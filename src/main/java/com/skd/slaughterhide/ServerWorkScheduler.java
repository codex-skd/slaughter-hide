package com.skd.slaughterhide;

import it.unimi.dsi.fastutil.ints.IntObjectPair;
import net.minecraft.server.TickTask;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Tiny server-work scheduler mirroring the one the original mod hides inside
 * {@code ButcheryMod.queueServerWork}: tasks are queued by the server thread
 * and drained on {@code ServerTickEvent.Post}, so delayed work (bleeding timer,
 * drop sweeping) runs safely on the tick thread.
 */
public final class ServerWorkScheduler {
    private static final Queue<IntObjectPair<Runnable>> PENDING = new ConcurrentLinkedQueue<>();
    private static final Queue<TickTask> WORK = new PriorityBlockingQueue<>(16, Comparator.comparingInt(TickTask::getTick));

    private ServerWorkScheduler() {
    }

    public static void queue(int delay, Runnable action) {
        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
            PENDING.add(IntObjectPair.of(delay, action));
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        int currentTick = event.getServer().getTickCount();
        IntObjectPair<Runnable> pending;
        while ((pending = PENDING.poll()) != null) {
            WORK.add(new TickTask(currentTick + pending.leftInt(), pending.value()));
        }
        TickTask due;
        while ((due = WORK.peek()) != null && currentTick >= due.getTick()) {
            WORK.poll().run();
        }
    }
}
package com.skd.slaughterhide.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class FreezerSmokeParticle extends SingleQuadParticle {
	private final SpriteSet spriteSet;

	protected FreezerSmokeParticle(ClientLevel level, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(level, x, y, z, spriteSet.first());
		this.spriteSet = spriteSet;
		this.setSize(0.2f, 0.2f);
		this.quadSize *= 1.2f;
		this.lifetime = Math.max(1, 32 + (this.random.nextInt(16) - 8));
		this.gravity = -0.1f;
		this.hasPhysics = true;
		this.xd = vx * -1.0;
		this.yd = vy * -1.0;
		this.zd = vz * -1.0;
		this.setSpriteFromAge(spriteSet);
	}

	@Override
	public Layer getLayer() {
		return Layer.OPAQUE;
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.removed) {
			this.setSprite(this.spriteSet.get(this.age / 6 % 8 + 1, 8));
		}
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public Provider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			return new FreezerSmokeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}
}

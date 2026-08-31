package com.skd.slaughterhide.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

/**
 * Small dark-red blood droplet: falls under gravity, tinted red on top of the
 * sprite, and disappears on landing. Replaces the lava drip particles the
 * bleeding step used to borrow from vanilla.
 */
public class BloodDripParticle extends SingleQuadParticle {
	protected BloodDripParticle(ClientLevel level, double x, double y, double z,
								double vx, double vy, double vz, SpriteSet spriteSet) {
		super(level, x, y, z, spriteSet.first());
		this.setSize(0.14f, 0.14f);
		this.quadSize *= 1.1f + this.random.nextFloat() * 0.5f;
		this.lifetime = 40 + this.random.nextInt(30);
		this.gravity = 0.22f;
		this.hasPhysics = true;
		this.friction = 0.98f;
		// keep the sprite's own dark-red colour (do NOT darken it further)
		this.rCol = 1.0f;
		this.gCol = 1.0f;
		this.bCol = 1.0f;
		this.xd = vx;
		this.yd = vy;
		this.zd = vz;
	}

	@Override
	public Layer getLayer() {
		return Layer.OPAQUE;
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.removed && this.onGround) {
			this.remove();
		}
	}

	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public Provider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
									   double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			return new BloodDripParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}
}

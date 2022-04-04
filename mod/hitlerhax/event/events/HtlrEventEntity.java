// 
// Decompiled by Procyon v0.5.36
// 

package mod.hitlerhax.event.events;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import mod.hitlerhax.event.HtlrEventCancellable;

public class HtlrEventEntity extends HtlrEventCancellable
{
    private Entity entity;
    
    public HtlrEventEntity(final Entity entity) {
        this.entity = entity;
    }
    
    public Entity get_entity() {
        return this.entity;
    }
    
    public void set_Entity(final Entity entity) {
        this.entity = entity;
    }
    
    public HtlrEventEntity(final Entity entityIn, final ICamera camera, final double camX, final double camY, final double camZ) {
        this.entity = entityIn;
    }
    
    public static class ScEventCollision extends HtlrEventEntity
    {
        private double x;
        private double y;
        private double z;
        
        public ScEventCollision(final Entity entity, final double x, final double y, final double z) {
            super(entity);
            this.x = x;
            this.y = y;
            this.z = z;
        }
        
        public void set_x(final double x) {
            this.x = x;
        }
        
        public void set_y(final double y) {
            this.y = y;
        }
        
        public void set_z(final double z) {
            this.z = z;
        }
        
        public double get_x() {
            return this.x;
        }
        
        public double get_y() {
            return this.y;
        }
        
        public double get_z() {
            return this.z;
        }
    }
}

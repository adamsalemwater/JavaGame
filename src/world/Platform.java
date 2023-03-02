package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;


public class Platform  {


  private static final BodyImage platformImage =  new BodyImage("data/platform.png");
  private World world;
  Shape platformShape;
  float x, y;


  public Platform(World world, float centreX, float centreY, float halfWidth, float halfheight) {
      this.x = centreX;
      this.y = centreY;
      this.world = world;
      platformShape = new BoxShape(halfWidth, halfheight);
      StaticBody platform = new StaticBody(this.world, platformShape);
      platform.setPosition(new Vec2(centreX, centreY));
//      platform.addImage(platformImage);
  }


    public World getWorld() {
        return world;
    }
}

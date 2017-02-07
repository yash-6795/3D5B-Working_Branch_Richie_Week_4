package tcd.game;


import android.content.Context;

/**
 * Created by stefano on 04/02/17.
 */

public class NPC extends GameObject {

    private int DIRECTION_X = 0;
    private int DIRECTION_Y  = 1;
    private int COUNT=3*50; //THIS VALUE SHOULD BE 3 TIMES OF FPS FOR RUNNING THE BOT IN ONE DIRECTION FOR THREE SECONDS
    private void swap_directions()
    {
        int temp;
        temp=this.DIRECTION_X;
        this.DIRECTION_X= -(this.DIRECTION_Y);
        this.DIRECTION_Y=temp;
    }

    NPC(Context context, String s,int canvasWidth, int canvasHeight){
        super(context,s, GameObjectTypes.NPC, canvasWidth, canvasHeight);
    }
    @Override
    protected void move()
    {   if(this.COUNT!=0)
        {
            collisionBox.left += velX*get_directionX();
            collisionBox.right += velX*get_directionX();
            collisionBox.top += velY*get_directionY();
            collisionBox.bottom += velY*get_directionY();
            this.COUNT--;
        }
        else
        {
            this.swap_directions();
            this.COUNT=3*50;
            collisionBox.left += velX*get_directionX();
            collisionBox.right += velX*get_directionX();
            collisionBox.top += velY*get_directionY();
            collisionBox.bottom += velY*get_directionY();
            this.COUNT--;
        }
    }

    @Override
    protected void unmove(){
        this.COUNT++;
        if(this.COUNT!=0)
        {
            collisionBox.left -= velX*get_directionX();
            collisionBox.right -= velX*get_directionX();
            collisionBox.top -= velY*get_directionY();
            collisionBox.bottom -= velY*get_directionY();
            this.COUNT--;this.COUNT--;//this will help when bot would not be able to move in next 3 seconds in the same direction then it would change itz direcction
        }


    }
    private int get_directionX()
    {
        return this.DIRECTION_X;
    }
    private int get_directionY()
    {
        return this.DIRECTION_Y;
    }

}
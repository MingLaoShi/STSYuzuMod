package YUZUMod.panel;

import YUZUMod.YuzuMod;
import YUZUMod.helper.ModHelper;
import YUZUMod.power.YUZUCriticalHitPower;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import static com.megacrit.cardcrawl.helpers.FontHelper.prepFont;

public class YUZUCriticalRatePanel extends AbstractPanel {
    public static int OriginMax=10;
    private final Color color;
    private int MAX;
    private Texture background= ImageMaster.loadImage(ModHelper.makeImgPath("UI/criticalPanel","background"));
    private static float ORB_IMG_SCALE = 1.2f * Settings.scale;

    public int getAmount() {
        return amount;
    }

    private int amount;
    private int modifiedMax;
    private static final float FontSize = 34.0F;
    private static final Color FONT_COLOR = new Color(1.0F, 1.0F, 0.86F, 1.0F);

    private static BitmapFont expPanelFont = prepFont(FontSize, true);
    private static ShapeRenderer shapeRenderer;
    private float progress=0.0F;
    private float nextProgress=0.0F;
    private float duration=0.0F;
    public YUZUCriticalRatePanel(float show_x, float show_y, float hide_x, float hide_y, Texture img, boolean startHidden) {
        super(show_x, show_y, hide_x, hide_y, img, startHidden);
        this.MAX=OriginMax;
        this.amount=0;
        this.modifiedMax=-1;
        shapeRenderer=new ShapeRenderer();
        this.color=YuzuMod.YUZUColor.cpy();
    }

    public void update(){

        if(this.duration>0.0F){
            this.duration-=Gdx.graphics.getDeltaTime();
            float step=(this.nextProgress-this.progress)/this.duration*Gdx.graphics.getDeltaTime();
            if(step>0.0F&&this.progress+step>=this.nextProgress||
                step<0.0F&&this.progress+step<=this.nextProgress){
                this.progress=nextProgress;
            }else{
                this.progress+=step;
            }
        }else{
            if(this.progress==1.0F){
                calaProgress();
            }
        }
    }

    public void render(SpriteBatch sb){
        //我不得已而为之啊
        this.current_x=AbstractDungeon.overlayMenu.energyPanel.current_x;
        this.current_y=AbstractDungeon.overlayMenu.energyPanel.current_y;


        sb.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.color);

        // 绘制弧形进度条
        drawArcProgressBar(shapeRenderer, current_x, current_y, 50* Settings.scale, 75*Settings.scale, 270, 360 * progress);

        shapeRenderer.end();
        sb.begin();
        sb.setColor(Color.WHITE.cpy());
//        sb.draw(background,current_x-background.getWidth()/2.0F,current_y-background.getHeight()/2.0F);
        sb.draw(background,
                current_x-70.0f, current_y-68.0f ,
                70.0f, 70.0f,
                196.0F, 140.0F,
                ORB_IMG_SCALE, ORB_IMG_SCALE,
                0.0F,
                0, 0,
                196, 140,
                false, false);
        this.renderNumbers(sb);
    }

    private void renderNumbers(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb,FontHelper.healthInfoFont,Integer.toString(this.amount),this.current_x+100*Settings.scale,this.current_y+30.0F*Settings.scale,Color.WHITE.cpy());
        int max=getMAX();
        FontHelper.renderFontCentered(sb,FontHelper.healthInfoFont,Integer.toString(max),this.current_x+100.0F*Settings.scale,this.current_y-10.0F*Settings.scale,Color.WHITE.cpy());
    }

    public int increase(int amount){
        this.amount+=amount;
        calaProgress();

        this.generatePower();
        return this.amount;
    }

    private void generatePower(){
        int max=this.MAX;
        if(this.modifiedMax>0){
            max=this.modifiedMax;
        }
        while(amount>=max){
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new InflameEffect(AbstractDungeon.player)));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new YUZUCriticalHitPower(AbstractDungeon.player,1)));
            this.amount-=max;
        }
    }

    public void reset(){
        this.MAX=OriginMax;
        this.amount=0;
        this.modifiedMax=-1;
        this.progress=this.nextProgress=0.0F;
    }

    public void setMAX(int max){
        this.modifiedMax=max;
        this.modifiedMax=Math.max(1,this.modifiedMax);
        calaProgress();
        generatePower();
    }
    public void changeMAX(int amount){
        this.MAX+=amount;
        this.MAX=Math.max(1,this.MAX);
        calaProgress();

        generatePower();
    }

    public void resetModifierMax(){
        this.modifiedMax=-1;
        calaProgress();

        generatePower();
    }

    public int getMAX(){
        return modifiedMax>0?modifiedMax:MAX;
    }

    private void drawArcProgressBar(ShapeRenderer renderer, float x, float y, float innerRadius, float outerRadius, float startAngle, float degrees) {
        int segments = 100;  // 细分数量越高，弧形越平滑
        float theta = (float) Math.toRadians(degrees) / segments;
        float cos = (float) Math.cos(theta);
        float sin = (float) Math.sin(theta);

        float outerX = outerRadius * (float) Math.cos(Math.toRadians(startAngle));
        float outerY = outerRadius * (float) Math.sin(Math.toRadians(startAngle));
        float innerX = innerRadius * (float) Math.cos(Math.toRadians(startAngle));
        float innerY = innerRadius * (float) Math.sin(Math.toRadians(startAngle));

        for (int i = 0; i < segments; i++) {
            float newOuterX = cos * outerX - sin * outerY;
            float newOuterY = sin * outerX + cos * outerY;
            float newInnerX = cos * innerX - sin * innerY;
            float newInnerY = sin * innerX + cos * innerY;

            renderer.triangle(x + innerX, y + innerY, x + outerX, y + outerY, x + newOuterX, y + newOuterY);
            renderer.triangle(x + innerX, y + innerY, x + newOuterX, y + newOuterY, x + newInnerX, y + newInnerY);

            outerX = newOuterX;
            outerY = newOuterY;
            innerX = newInnerX;
            innerY = newInnerY;
        }
    }

    private void calaProgress(){
        int tempMax=getMAX();
        this.nextProgress= (float) this.amount /tempMax;
        this.nextProgress=Math.min(nextProgress,1.0F);
        this.duration=0.5F;
    }
}

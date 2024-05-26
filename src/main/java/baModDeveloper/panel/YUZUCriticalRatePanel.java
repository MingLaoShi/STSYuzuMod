package baModDeveloper.panel;

import baModDeveloper.power.YUZUCriticalHitPower;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;

import static com.megacrit.cardcrawl.helpers.FontHelper.prepFont;

public class YUZUCriticalRatePanel extends AbstractPanel {
    private int MAX;
    private int amount;
    private int modifiedMax;
    private static final float FontSize = 34.0F;
    private static final Color FONT_COLOR = new Color(1.0F, 1.0F, 0.86F, 1.0F);

    private static BitmapFont expPanelFont = prepFont(FontSize, true);


    public YUZUCriticalRatePanel(float show_x, float show_y, float hide_x, float hide_y, Texture img, boolean startHidden) {
        super(show_x, show_y, hide_x, hide_y, img, startHidden);
        this.MAX=5;
        this.amount=0;
        this.modifiedMax=-1;
    }

    public void update(){
        if (this.target_x != this.current_x) {
            this.current_x = this.target_x;
        }
        if (this.target_y != this.current_y) {
            this.current_y = this.target_y;
        }
    }

    public void render(SpriteBatch sb){
        FontHelper.renderFontCentered(sb, expPanelFont, this.amount+"/"+(this.modifiedMax>0?this.modifiedMax:this.MAX), this.current_x + 20.0F * Settings.scale, this.current_y , FONT_COLOR);
    }

    public int increase(int amount){
        this.amount+=amount;
        this.generatePower();
        return this.amount;
    }

    private void generatePower(){
        int max=this.MAX;
        if(this.modifiedMax>0){
            max=this.modifiedMax;
        }
        while(amount>=max){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new YUZUCriticalHitPower(AbstractDungeon.player,1)));
            this.amount-=max;
        }
    }

    public void reset(){
        this.MAX=5;
        this.amount=0;
        this.modifiedMax=-1;
    }

    public void setMAX(int max){
        this.modifiedMax=max;
        generatePower();
    }
    public void changeMAX(int amount){
        this.MAX+=amount;
        generatePower();
    }

}

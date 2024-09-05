package YUZUMod.potion;

import YUZUMod.helper.ModHelper;
import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;

public class YUZUParallelPotion extends CustomPotion {
    public static final String ID = ModHelper.makePath("ParallelPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final PotionRarity rarity = PotionRarity.RARE;
    private static final PotionSize size = PotionSize.HEART;
    private static final PotionColor color = PotionColor.BLUE;
    public static Color liquidColor = new Color(159.0F / 255.0F, 165.0F / 255.0F, 290.0F / 255.0F, 1.0F);
    public static Color hybridColor = new Color(233.0F / 255.0F, 233.0F / 255.0F, 233.0F / 255.0F, 1.0F);
    public static Color spotsColor = new Color(254.0F / 255.0F, 168.0F / 255.0F, 198.0F / 255.0F, 1.0F);

    public YUZUParallelPotion() {
        super(potionStrings.NAME, ID, rarity, size, color);
        this.isThrown=true;
        this.targetRequired=false;
    }

    @Override
    public void initializeData() {
        this.potency=getPotency();
        this.description=String.format(potionStrings.DESCRIPTIONS[0],this.potency);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));

    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        for(int i=0;i<this.potency;i++){
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractDungeon.player.potionSlots+=1;
                    AbstractDungeon.player.potions.add(new PotionSlot(AbstractDungeon.player.potionSlots - 1));
                    this.isDone=true;
                }
            });
        }

    }

    @Override
    public int getPotency(int i) {
        return 1;

    }

    @Override
    public AbstractPotion makeCopy() {
        return new YUZUParallelPotion();
    }
}

package YUZUMod.potion;

import YUZUMod.helper.ModHelper;
import YUZUMod.power.YUZUCriticalHitPower;
import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class YUZUCriticalHitPotion extends CustomPotion {
    public static final String ID = ModHelper.makePath("CriticalHitPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final PotionRarity rarity = PotionRarity.UNCOMMON;
    private static final PotionSize size = PotionSize.FAIRY;
    private static final PotionColor color = PotionColor.FIRE;
    public static Color liquidColor = new Color(159.0F / 255.0F, 165.0F / 255.0F, 290.0F / 255.0F, 1.0F);
    public static Color hybridColor = new Color(233.0F / 255.0F, 233.0F / 255.0F, 233.0F / 255.0F, 1.0F);
    public static Color spotsColor = new Color(254.0F / 255.0F, 168.0F / 255.0F, 198.0F / 255.0F, 1.0F);

    public YUZUCriticalHitPotion() {
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
        addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new YUZUCriticalHitPower(AbstractDungeon.player,this.getPotency())));
    }

    @Override
    public int getPotency(int i) {
        return 2;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new YUZUCriticalHitPotion();
    }
}

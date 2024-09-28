package YUZUMod.potion;

import YUZUMod.helper.ModHelper;
import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class YUZUYuedongPotion extends CustomPotion {
    public static final String ID = ModHelper.makePath("YuedongPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final PotionRarity rarity = PotionRarity.RARE;
    private static final PotionSize size = PotionSize.H;
    private static final PotionColor color = PotionColor.FIRE;
    public static Color liquidColor = new Color(159.0F / 255.0F, 165.0F / 255.0F, 290.0F / 255.0F, 1.0F);
    public static Color hybridColor = new Color(233.0F / 255.0F, 233.0F / 255.0F, 233.0F / 255.0F, 1.0F);
    public static Color spotsColor = new Color(254.0F / 255.0F, 168.0F / 255.0F, 198.0F / 255.0F, 1.0F);

    public YUZUYuedongPotion() {
        super(potionStrings.NAME, ID, rarity, size, color);
        this.isThrown=true;
        this.targetRequired=true;
    }

    @Override
    public void initializeData() {
        this.potency=getPotency();
        this.description=potionStrings.DESCRIPTIONS[this.potency-1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));

    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if(abstractCreature instanceof AbstractMonster){
            int damage=((AbstractMonster) abstractCreature).getIntentDmg();
            addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new VigorPower(AbstractDungeon.player,damage*this.potency)));
        }
    }

    @Override
    public int getPotency(int i) {
        return 1;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new YUZUYuedongPotion();
    }
}

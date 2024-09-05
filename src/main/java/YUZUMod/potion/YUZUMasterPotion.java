package YUZUMod.potion;

import YUZUMod.helper.ModHelper;
import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class YUZUMasterPotion extends CustomPotion {
    public static final String ID = ModHelper.makePath("AcceleratePotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(ID);
    private static final PotionRarity rarity = PotionRarity.UNCOMMON;
    private static final PotionSize size = PotionSize.CARD;
    private static final PotionColor color = PotionColor.BLUE;
    public static Color liquidColor = new Color(255.0F / 255.0F, 100.0F / 255.0F, 1.0F / 255.0F, 1.0F);
    public static Color hybridColor = new Color(233.0F / 255.0F, 233.0F / 255.0F, 233.0F / 255.0F, 1.0F);
    public static Color spotsColor = new Color(254.0F / 255.0F, 168.0F / 255.0F, 198.0F / 255.0F, 1.0F);

    public YUZUMasterPotion() {
        super(potionStrings.NAME, ID, rarity, size, color);
    }

    @Override
    public void use(AbstractCreature abstractCreature) {

    }

    @Override
    public int getPotency(int i) {
        return 0;
    }

    @Override
    public AbstractPotion makeCopy() {
        return null;
    }
}

package YUZUMod.cards;

import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import YUZUMod.helper.YUZUPotionTarget;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class YUZUBarter extends YUZUCustomCard {
    public static final String ID = ModHelper.makePath("Barter");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = ModHelper.makeCardImagePath(ID);
    private static final int COST = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET = YUZUPotionTarget.POTION;
    private static final CardRarity RARITY = CardRarity.RARE;

    public YUZUBarter() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDescription(CARD_STRINGS.UPGRADE_DESCRIPTION);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractPotion p = YUZUPotionTarget.getTarget(this);
        if (p != null) {
            addToBot(new AbstractGameAction() {
                boolean upgraded=YUZUBarter.this.upgraded;
                @Override
                public void update() {
                    AbstractDungeon.player.removePotion(p);
                    AbstractPotion.PotionRarity rarity1 = p.rarity;
                    if(upgraded){
                        rarity1= AbstractPotion.PotionRarity.UNCOMMON;
                    }
                    switch (rarity1) {
                        case COMMON:
                            addToTop(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.UNCOMMON, true)));
                            break;
                        case UNCOMMON:
                            addToTop(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.RARE, true)));
                            break;
                        case RARE:
                        default:
                            break;
                    }

                    this.isDone = true;
                }
            });
        }

    }
}

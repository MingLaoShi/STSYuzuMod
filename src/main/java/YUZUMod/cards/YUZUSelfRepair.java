package YUZUMod.cards;

import YUZUMod.action.YUZUSelfRepairAction;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.MummifiedHand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class YUZUSelfRepair extends YUZUCustomCard {
    public static final String ID = ModHelper.makePath("SelfRepair");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = ModHelper.makeCardImagePath(ID);
    private static final int COST = -1;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;

    public YUZUSelfRepair() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    protected void upgradeMethod() {
        this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new YUZUSelfRepairAction(this.energyOnUse, this.freeToPlayOnce, false, this.upgraded));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer, abstractMonster);
        addToBot(new AbstractGameAction() {
            private final Logger logger = LogManager.getLogger(MummifiedHand.class.getName());

            @Override
            public void update() {
                ArrayList<AbstractCard> groupCopy = new ArrayList<>();
                for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {
                    if (abstractCard.cost > 0 && abstractCard.costForTurn > 0 && !abstractCard.freeToPlayOnce&&abstractCard.type==CardType.POWER) {
                        groupCopy.add(abstractCard);
                        continue;
                    }
                    logger.info("COST IS 0: " + abstractCard.name);
                }


                for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
                    if (i.card != null) {
                        logger.info("INVALID: " + i.card.name);
                        groupCopy.remove(i.card);
                    }
                }

                AbstractCard c = null;
                if (!groupCopy.isEmpty()) {
                    logger.info("VALID CARDS: ");
                    for (AbstractCard cc : groupCopy) {
                        logger.info(cc.name);
                    }

                    c = groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
                } else {
                    logger.info("NO VALID CARDS");
                }

                if (c != null) {
                    logger.info("Mummified hand: " + c.name);
                    c.setCostForTurn(0);
                } else {
                    logger.info("ERROR: MUMMIFIED HAND NOT WORKING");
                }
                this.isDone=true;
            }
        });
    }
}

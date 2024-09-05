package YUZUMod.cards;

import YUZUMod.action.DrawOrDisCardToHandAction;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;

import java.util.function.Consumer;

public class YUZUEmergencyProtection extends YUZUCustomCard {
    public static final String ID= ModHelper.makePath("EmergencyProtection");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=0;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.COMMON;

    public YUZUEmergencyProtection() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock=this.block=4;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBlock(2);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        addToBot(new AbstractGameAction() {
//            @Override
//            public void update() {
//                for(AbstractCard card: AbstractDungeon.player.hand.group){
//                    if(card.retain||card.selfRetain){
//                        addToTop(new ExhaustSpecificCardAction(card,AbstractDungeon.player.hand));
//                    }else {
//                        addToTop(new DiscardSpecificCardAction(card));
//                    }
//                }
//                this.isDone=true;
//            }
//        });
        addToBot(new GainBlockAction(abstractPlayer,this.block));
    }


    @SpirePatch(clz = ApplyPowerAction.class, method = "update")
    public static class ArrayPatch {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(ApplyPowerAction $this, AbstractPower ___powerToApply) {
            if ($this.target.isPlayer && ___powerToApply.type == AbstractPower.PowerType.DEBUFF && !$this.target.hasPower(___powerToApply.ID)) {
                AbstractPlayer p = AbstractDungeon.player;
                Consumer<AbstractCard> func = (card)->{
                    AbstractDungeon.actionManager.addToBottom(new DrawOrDisCardToHandAction(card));
                };
                p.drawPile.group.stream().filter(c -> YUZUEmergencyProtection.ID.equals(c.cardID)).forEach(func);
                p.discardPile.group.stream().filter(c -> YUZUEmergencyProtection.ID.equals(c.cardID)).forEach(func);
//                p.exhaustPile.group.stream().filter(c -> YUZUEmergencyProtection.ID.equals(c.cardID)).forEach(func);
//                p.hand.group.stream().filter(c -> StarlessNight.ID.equals(c.cardID)).forEach(func);
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasRelic");
                return new int[] { LineFinder.findAllInOrder(ctMethodToPatch, (Matcher)matcher)[0] };
            }
        }
    }
}

package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class YUZUMemoryRetention extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("MemoryRetention");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.RARE;

    public YUZUMemoryRetention() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    protected void upgradeMethod() {
        this.updateCost(-1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.player.discardPile.group.addAll(AbstractDungeon.player.exhaustPile.group);
                AbstractDungeon.player.exhaustPile.clear();
                this.isDone=true;
            }
        });
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> cards=AbstractDungeon.player.hand.group;
                addToTop(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractDungeon.player.exhaustPile.group.addAll(cards);
                        this.isDone=true;
                    }
                });
                for(AbstractCard c:cards){
                    addToTop(new MakeTempCardInHandAction(c));
                }
                this.isDone=true;
            }
        });
    }
}

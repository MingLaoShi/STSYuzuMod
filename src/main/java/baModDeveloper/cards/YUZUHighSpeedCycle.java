package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

public class YUZUHighSpeedCycle extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("HighSpeedCycle");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUHighSpeedCycle() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=3;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        addToBot(new FetchAction(abstractPlayer.discardPile,(card)->true,this.magicNumber,this::callback));
        addToBot(new AbstractGameAction() {
            {
                this.amount=YUZUHighSpeedCycle.this.magicNumber;
            }
            @Override
            public void update() {
                CardGroup group=new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                group.group.addAll(AbstractDungeon.player.discardPile.group);
                for(int i=0;i<Math.min(this.amount,group.size());i++){
                    AbstractCard c=group.getRandomCard(true);
                    addToTop(new DiscardToHandAction(c));
                    YUZUCustomCard.removeMaster(c);
                    group.removeCard(c);
                }
                this.isDone=true;
            }
        });
    }

    private void callback(List<AbstractCard> cards) {
        cards.forEach(YUZUCustomCard::removeMaster);
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new FetchAction(abstractPlayer.discardPile,(card)->true,this.magicNumber,this::callback));
    }
}

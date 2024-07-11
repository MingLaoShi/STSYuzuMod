package YUZUMod.cards;

import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class YUZUNovicePickUp extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("NovicePickUp");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.COMMON;

    public YUZUNovicePickUp() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=1;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBaseCost(0);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for(int i=0;i<this.magicNumber;i++){
            ArrayList<AbstractCard> cardlist= (ArrayList<AbstractCard>) CardLibrary.getAllCards().stream().filter(card->card.cost==0&&card.color!=CardColor.COLORLESS&&card.color!=CardColor.CURSE).collect(Collectors.toList());
            AbstractCard card=cardlist.get(AbstractDungeon.cardRandomRng.random(0,cardlist.size()-1)).makeCopy();
            if(card!=null){
                addToBot(new MakeTempCardInHandAction(card,true));
            }
        }

    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for(int i=0;i<this.magicNumber;i++){
            ArrayList<AbstractCard> cardlist= (ArrayList<AbstractCard>) CardLibrary.getAllCards().stream().filter(card -> card.color!=CardColor.COLORLESS&&card.color!=CardColor.CURSE).collect(Collectors.toList());
            AbstractCard card=cardlist.get(AbstractDungeon.cardRandomRng.random(0,cardlist.size()-1)).makeCopy();
            if(card!=null){
                card.setCostForTurn(0);
                addToBot(new MakeTempCardInHandAction(card,true));
            }
        }
    }
}

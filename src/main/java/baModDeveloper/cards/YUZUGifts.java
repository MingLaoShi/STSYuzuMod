package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class YUZUGifts extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("Gifts");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUGifts() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=2;
    }

    @Override
    protected void upgradeMethod() {
        upgradeMagicNumber(1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> cardGroup= CardLibrary.getAllCards();
        ArrayList<AbstractCard> temp= (ArrayList<AbstractCard>) cardGroup.stream().filter(card -> card.selfRetain).collect(Collectors.toList());
        if(!temp.isEmpty()){
            for(int i=0;i<this.magicNumber;i++){
                addToBot(new MakeTempCardInHandAction(temp.get(cardRandomRng.random(0,temp.size()-1)).makeCopy()));
            }
        }
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
    }

}

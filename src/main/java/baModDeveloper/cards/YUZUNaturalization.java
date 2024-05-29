package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.modifier.YUZUDrawCardModifier;
import baModDeveloper.modifier.YUZUExhaustModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUNaturalization extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("Naturalization");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeImgPath("card","default");
    private static final int COST=3;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.RARE;

    public YUZUNaturalization() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=5;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(3);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for(int i=0;i<this.magicNumber;i++){
            AbstractCard card= AbstractDungeon.returnTrulyRandomCard().makeCopy();
            card.freeToPlayOnce=true;
            if(card instanceof YUZUCustomCard)
                YUZUCustomCard.masterCard((YUZUCustomCard) card);
            CardModifierManager.addModifier(card,new YUZUExhaustModifier());
            this.addToBot(new MakeTempCardInDrawPileAction(card,1,true,true));
        }

    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for(int i=0;i<this.magicNumber;i++){
            AbstractCard card= AbstractDungeon.returnTrulyRandomCard().makeCopy();
            card.freeToPlayOnce=true;
            if(card instanceof YUZUCustomCard)
                YUZUCustomCard.masterCard((YUZUCustomCard) card);
            CardModifierManager.addModifier(card,new YUZUExhaustModifier());
            CardModifierManager.addModifier(card,new YUZUDrawCardModifier(1));
            this.addToBot(new MakeTempCardInDrawPileAction(card,1,true,true));
        }
    }
}

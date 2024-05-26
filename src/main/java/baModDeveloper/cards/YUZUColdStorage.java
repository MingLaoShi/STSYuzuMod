package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.modifier.BATwinsRetainModifier;
import baModDeveloper.modifier.YUZUCanNotPlayModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

public class YUZUColdStorage extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("ColdStorage");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeImgPath("card","default");
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUColdStorage() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=1;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsAction(AbstractDungeon.player.drawPile.group,this.magicNumber,"//",false,card -> true,this::callback));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster, int masterNum) {
        CardGroup cardGroup=new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        cardGroup.group.addAll(AbstractDungeon.player.drawPile.group);
        cardGroup.group.addAll(AbstractDungeon.player.exhaustPile.group);
        addToBot(new SelectCardsAction(cardGroup.group,this.magicNumber,"//",false,card -> true,this::callback));
    }

    private void callback(List<AbstractCard> cards){
        for(AbstractCard card:cards){
            CardModifierManager.addModifier(card,new YUZUCanNotPlayModifier());
            CardModifierManager.addModifier(card,new BATwinsRetainModifier());
            AbstractDungeon.player.hand.moveToHand(card);
            AbstractDungeon.player.drawPile.removeCard(card);
            AbstractDungeon.player.exhaustPile.removeCard(card);
        }
    }
}

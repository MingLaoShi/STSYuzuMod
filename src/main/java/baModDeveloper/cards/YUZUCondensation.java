package baModDeveloper.cards;

import baModDeveloper.helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.modifier.YUZUMasterPlayOnceMoreModifier;
import baModDeveloper.modifier.YUZUPlayOnceMoreModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

public class YUZUCondensation extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("Condensation");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=2;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUCondensation() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=1;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBaseCost(1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsInHandAction(1,"//",card->true,this::callback));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsInHandAction(1,"//",card->true,this::callbackMaster));
    }

    private void callback(List<AbstractCard> cards){
        for(AbstractCard card:cards){
            card.modifyCostForCombat(1);
            CardModifierManager.addModifier(card,new YUZUPlayOnceMoreModifier());
        }
    }
    private void callbackMaster(List<AbstractCard> cards){
        for(AbstractCard card:cards){
            card.modifyCostForCombat(1);
            CardModifierManager.addModifier(card,new YUZUPlayOnceMoreModifier());
            CardModifierManager.addModifier(card,new YUZUMasterPlayOnceMoreModifier());
        }
    }
}

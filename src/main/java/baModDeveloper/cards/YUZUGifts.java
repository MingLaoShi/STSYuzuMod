package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class YUZUGifts extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("Gifts");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeImgPath("card","default");
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.BASIC;

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
        for(int i=0;i<this.magicNumber;i++){
            AbstractCard card=returnRandomRetainCardInCombat();
            if(card!=null){
                addToBot(new MakeTempCardInHandAction(card.makeCopy()));
            }
        }
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster, int masterNum) {

    }

    public static AbstractCard returnRandomRetainCardInCombat(){
        ArrayList<AbstractCard> list = new ArrayList();
        Iterator var1 = srcCommonCardPool.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (!c.hasTag(AbstractCard.CardTags.HEALING)&&c.selfRetain) {
                list.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }

        var1 = srcUncommonCardPool.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (!c.hasTag(AbstractCard.CardTags.HEALING)&&c.selfRetain) {
                list.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }

        var1 = srcRareCardPool.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (!c.hasTag(AbstractCard.CardTags.HEALING)&&c.selfRetain) {
                list.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }
        if(list.isEmpty()){
            return null;
        }
        return (AbstractCard)list.get(cardRandomRng.random(list.size() - 1));
    }
}

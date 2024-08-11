//package YUZUMod.cards;
//
//import YUZUMod.action.EasyXCostAction;
//import YUZUMod.character.YuzuCharacter;
//import YUZUMod.helper.ModHelper;
//import YUZUMod.power.YUZUBurningPower;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
//import java.util.function.BiFunction;
//
//public class YUZUSpark extends YUZUCustomCard{
//    public static final String ID= ModHelper.makePath("Spark");
//    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
//    private static final String NAME=CARD_STRINGS.NAME;
//    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
//    private static final int COST=-1;
//    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
//    private static final CardType TYPE=CardType.SKILL;
//    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
//    private static final CardTarget TARGET=CardTarget.ENEMY;
//    private static final CardRarity RARITY=CardRarity.COMMON;
//
//    public YUZUSpark() {
//        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//        this.baseMagicNumber=this.magicNumber=4;
//    }
//
//    @Override
//    protected void upgradeMethod() {
//        this.upgradeMagicNumber(2);
//    }
//
//    @Override
//    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        BiFunction<Integer, int[], Boolean> function=new BiFunction<Integer, int[], Boolean>() {
//            @Override
//            public Boolean apply(Integer integer, int[] ints) {
//                for(int i=0;i<integer;i++)
//                    addToTop(new ApplyPowerAction(abstractMonster, abstractPlayer,new YUZUBurningPower(abstractMonster, abstractPlayer, ints[0])));
//                return true;
//            }
//        };
//        addToBot(new EasyXCostAction(this,function,this.magicNumber));
//    }
//
//}

//package YUZUMod.cards;
//
//import YUZUMod.action.YUZUDoubleBurningAction;
//import YUZUMod.character.YuzuCharacter;
//import YUZUMod.helper.ModHelper;
//import YUZUMod.power.YUZUBurningPower;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
//import com.megacrit.cardcrawl.cards.status.Burn;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
//public class YUZUSlowBurn extends YUZUCustomCard{
//    public static final String ID= ModHelper.makePath("SlowBurn");
//    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
//    private static final String NAME=CARD_STRINGS.NAME;
//    private static final String IMG_PATH= ModHelper.makeCardImagePath(ID);
//    private static final int COST=1;
//    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
//    private static final CardType TYPE=CardType.SKILL;
//    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
//    private static final CardTarget TARGET=CardTarget.ALL;
//    private static final CardRarity RARITY=CardRarity.UNCOMMON;
//
//    public YUZUSlowBurn() {
//        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//        this.exhaust=true;
//        this.baseMagicNumber=this.magicNumber=2;
//        this.cardsToPreview=new Burn();
//    }
//
//    @Override
//    protected void upgradeMethod() {
//        this.upgradeMagicNumber(1);
//    }
//
//    @Override
//    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        for(AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters){
//            addToBot(new ApplyPowerAction(m,abstractPlayer,new YUZUBurningPower(m,abstractPlayer,1)));
//        }
//        for(AbstractMonster m:AbstractDungeon.getCurrRoom().monsters.monsters){
//            addToBot(new YUZUDoubleBurningAction(m,this.magicNumber-1));
//        }
//        addToBot(new MakeTempCardInHandAction(new Burn()));
//    }
//}

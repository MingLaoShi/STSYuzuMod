//package YUZUMod.cards;
//
//import YUZUMod.character.YuzuCharacter;
//import YUZUMod.helper.ModHelper;
//import YUZUMod.power.YUZUBurningPower;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
//public class YUZUScorchingExplosive extends YUZUCustomCard{
//    public static final String ID= ModHelper.makePath("ScorchingExplosive");
//    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
//    private static final String NAME=CARD_STRINGS.NAME;
//    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
//    private static final int COST=1;
//    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
//    private static final CardType TYPE=CardType.ATTACK;
//    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
//    private static final CardTarget TARGET=CardTarget.ALL_ENEMY;
//    private static final CardRarity RARITY=CardRarity.COMMON;
//    private int extraDamage;
//
//    public YUZUScorchingExplosive() {
//        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//        this.baseDamage=this.damage=3;
//        this.baseMagicNumber=this.magicNumber=3;
//        this.baseBlock=this.block=1;
//        this.isMultiDamage=true;
//    }
//
//    @Override
//    protected void upgradeMethod() {
//        this.upgradeDamage(1);
//        this.upgradeMagicNumber(1);
//        this.upgradeBaseCost(1);
//        this.upgradeBlock(1);
//    }
//
//    @Override
//    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        addToBot(new DamageAllEnemiesAction(abstractPlayer,this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
//        for(AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters){
//            if(!m.isDeadOrEscaped()){
//                addToBot(new ApplyPowerAction(m,abstractPlayer,new YUZUBurningPower(m,abstractPlayer,this.magicNumber)));
//            }
//        }
//    }
//
//
//
//    @Override
//    protected void applyPowersToBlock() {
//
//    }
//
//    @Override
//    public void applyPowers() {
//        int masterNum=YUZUCustomCard.isMastered(this);
//        int baseBaseDamage=this.baseDamage;
//        this.baseDamage+=masterNum*this.baseBlock;
//        int baseBaseMagic=this.baseMagicNumber;
//        this.baseMagicNumber+=masterNum*this.baseBlock;
//        super.applyPowers();
//        this.magicNumber=this.baseMagicNumber;
//        this.isDamageModified=this.damage!=baseBaseDamage;
//        this.baseDamage=baseBaseDamage;
//        this.isMagicNumberModified=this.magicNumber!=baseBaseMagic;
//        this.baseMagicNumber=baseBaseMagic;
//    }
//
//    @Override
//    public void calculateCardDamage(AbstractMonster mo) {
//        int masterNum=YUZUCustomCard.isMastered(this);
//        int baseBaseDamage=this.baseDamage;
//        this.baseDamage+=masterNum;
//        int baseBaseMagic=this.baseMagicNumber;
//        this.baseMagicNumber+=masterNum;
//        super.calculateCardDamage(mo);
//
//        this.isDamageModified=this.damage!=baseBaseDamage;
//        this.baseDamage=baseBaseDamage;
//        this.isMagicNumberModified=this.magicNumber!=baseBaseMagic;
//        this.baseMagicNumber=baseBaseMagic;
//    }
//}

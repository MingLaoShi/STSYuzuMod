//package baModDeveloper.cards;
//
//import baModDeveloper.character.YuzuCharacter;
//import baModDeveloper.helper.ModHelper;
//import baModDeveloper.power.YUZUBurningPower;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
//import static baModDeveloper.power.YUZUBurningPower.BASEDAMAGE;
//
//public class YUZUSpark extends YUZUCustomCard{
//    public static final String ID= ModHelper.makePath("Spark");
//    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
//    private static final String NAME=CARD_STRINGS.NAME;
//    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
//    private static final int COST=1;
//    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
//    private static final CardType TYPE=CardType.ATTACK;
//    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
//    private static final CardTarget TARGET=CardTarget.ENEMY;
//    private static final CardRarity RARITY=CardRarity.UNCOMMON;
//
//    public YUZUSpark() {
//        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//        this.baseMagicNumber=this.magicNumber=4;
//    }
//
//    @Override
//    protected void upgradeMethod() {
//        this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION;
//        this.initializeDescription();
//    }
//
//    @Override
//    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        addToBot(new ApplyPowerAction(abstractMonster,abstractPlayer,new YUZUBurningPower(abstractMonster, abstractPlayer, this.magicNumber)));
//
//        addToBot(new AbstractGameAction() {
//
//            AbstractCard card=YUZUSpark.this;
//            AbstractMonster m=abstractMonster;
//            boolean upgrade=YUZUSpark.this.upgraded;
//            @Override
//            public void update() {
//                if(AbstractDungeon.player.hasPower(YUZUBurningPower.POWER_ID)){
//                    this.amount=AbstractDungeon.player.getPower(YUZUBurningPower.POWER_ID).amount;
//                }else {
//                    this.amount=0;
//                }
//                int damage=(int) (BASEDAMAGE*Math.pow(2, (this.amount / 10) ));
//                if(upgrade){
//                    damage+= (int) (BASEDAMAGE*Math.pow(2, (this.amount-1 / 10) ));
//                }
//                this.card.baseDamage=damage;
//                this.card.calculateCardDamage(this.m);
//                damage=this.card.damage;
//                addToTop(new DamageAction(abstractMonster,new DamageInfo(AbstractDungeon.player,damage),AttackEffect.FIRE));
//                this.isDone=true;
//            }
//        });
//    }
//}

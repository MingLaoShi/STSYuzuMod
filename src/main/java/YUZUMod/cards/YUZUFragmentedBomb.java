//package baModDeveloper.cards;
//
//import baModDeveloper.helper.ModHelper;
//import baModDeveloper.action.YUZUPlayTempCardAction;
//import baModDeveloper.character.YuzuCharacter;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
//public class YUZUFragmentedBomb extends YUZUCustomCard{
//    public static final String ID= ModHelper.makePath("FragmentedBomb");
//    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
//    private static final String NAME=CARD_STRINGS.NAME;
//    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
//    private static final int COST=2;
//    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
//    private static final CardType TYPE=CardType.ATTACK;
//    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
//    private static final CardTarget TARGET=CardTarget.ALL_ENEMY;
//    private static final CardRarity RARITY=CardRarity.UNCOMMON;
//
//    public YUZUFragmentedBomb() {
//        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//        this.baseDamage=this.damage=23;
//    }
//
//    @Override
//    protected void upgradeMethod() {
//        upgradeDamage(5);
//    }
//
//    @Override
//    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        int totalDamage = this.damage;
//        int numEnemies = (int) AbstractDungeon.getCurrRoom().monsters.monsters.stream().filter(m->!m.isDeadOrEscaped()).count();
//        int[] damageArray = new int[numEnemies];
//        // 随机分配伤害
//        while (totalDamage > 0) {
//            int randomIndex = AbstractDungeon.cardRandomRng.random(numEnemies - 1);
//            int damageToAdd = 1; // 每次分配1点伤害
//            damageArray[randomIndex] += damageToAdd;
//            totalDamage -= damageToAdd;
//        }
//        int[] truedamageArray=new int[AbstractDungeon.getCurrRoom().monsters.monsters.size()];
//        for(int i=0,j=0;i<AbstractDungeon.getCurrRoom().monsters.monsters.size();i++){
//            if(!AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDeadOrEscaped()){
//                truedamageArray[i]=damageArray[j];
//                j++;
//            }else{
//                truedamageArray[i]=0;
//            }
//        }
//        addToBot(new DamageAllEnemiesAction(abstractPlayer,truedamageArray, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING));
//    }
//
//
//
//    @Override
//    public void triggerOnCriticalHit(AbstractCreature target) {
//        AbstractCard card=this.makeSameInstanceOf();
//        card.purgeOnUse=true;
//        addToBot(new YUZUPlayTempCardAction(card,null));
//    }
//}

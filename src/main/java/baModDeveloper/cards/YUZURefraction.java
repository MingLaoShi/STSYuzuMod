//package baModDeveloper.cards;
//
//import baModDeveloper.helper.ModHelper;
//import baModDeveloper.action.YUZURefractionAction;
//import baModDeveloper.character.YuzuCharacter;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
//public class YUZURefraction extends YUZUCustomCard{
//    public static final String ID= ModHelper.makePath("Refraction");
//    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
//    private static final String NAME=CARD_STRINGS.NAME;
//    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
//    private static final int COST=-1;
//    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
//    private static final CardType TYPE=CardType.SKILL;
//    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
//    private static final CardTarget TARGET=CardTarget.NONE;
//    private static final CardRarity RARITY=CardRarity.UNCOMMON;
//
//    public YUZURefraction() {
//        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//    }
//
//    @Override
//    protected void upgradeMethod() {
//        this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION;
//        initializeDescription();
//    }
//
//    @Override
//    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        addToBot(new YUZURefractionAction(this.energyOnUse,this.freeToPlayOnce,false));
//    }
//
//    @Override
//    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        addToBot(new YUZURefractionAction(this.energyOnUse,this.freeToPlayOnce,true));
//    }
//
//}

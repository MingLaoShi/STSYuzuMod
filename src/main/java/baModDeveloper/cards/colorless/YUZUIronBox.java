package baModDeveloper.cards.colorless;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.action.YUZUClearCriticalRateAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUIronBox extends CustomCard {
    public static final String ID= ModHelper.makePath("IronBox");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= CardColor.COLORLESS;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.SPECIAL;

    public YUZUIronBox() {
        super(ID,NAME,IMG_PATH,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
        this.baseBlock=this.block=16;
        this.exhaust=true;
    }

    @Override
    public void upgrade() {
        this.upgradeBlock(4);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        addToBot(new YUZUClearCriticalRateAction());
    }
}

package YUZUMod.cards;

import YUZUMod.action.YUZUFilteredDrawCardAction;
import YUZUMod.action.YUZUPlayHandCardAction;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUFoldUpClothes extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("FoldUpClothes");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH= ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.COMMON;

    public YUZUFoldUpClothes() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock=this.block=8;
        this.baseMagicNumber=this.magicNumber=1;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBlock(3);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        addToBot(new YUZUFilteredDrawCardAction(this.magicNumber,this::filter,false,null));

    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                CardGroup group=new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                AbstractDungeon.player.hand.group.stream().filter(card -> card.hasTag(CardTags.STARTER_DEFEND)).forEach(group::addToBottom);
                if(!group.isEmpty()){
                    addToTop(new YUZUPlayHandCardAction(group.getRandomCard(true),null));
                }
                this.isDone=true;
            }
        });
    }

    private boolean filter(AbstractCard card) {
        return card.costForTurn==1;
    }
}

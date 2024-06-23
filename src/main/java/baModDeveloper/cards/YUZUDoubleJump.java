package baModDeveloper.cards;

import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.helper.ModHelper;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.NewExpr;

import java.util.HashSet;
import java.util.Set;

public class YUZUDoubleJump extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("DoubleJump");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH= ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;
    private static boolean canDraw=false;
    private static Set<String> cardsCanDraw=new HashSet<String>();
    static {
        cardsCanDraw.add(YUZUDoubleJump.ID);
    }

    public YUZUDoubleJump() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

    }

    @Override
    protected void upgradeMethod() {
        this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeDescription();
        this.selfRetain=true;
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       if(canDraw){
           addToBot(new ExpertiseAction(abstractPlayer, 10));
       }
    }

    private boolean drawCheck(AbstractCard card,String method) throws NotFoundException, CannotCompileException {
        final boolean[] res = {false};
        ClassPool pool= Loader.getClassPool();
        CtClass ctClass=pool.get(card.getClass().getName());
        ctClass.defrost();
        CtMethod useMethod=ctClass.getDeclaredMethod(method);
        useMethod.instrument(new ExprEditor(){
            @Override
            public void edit(NewExpr e) throws CannotCompileException {
                try{
                    CtConstructor ctConstructor=e.getConstructor();
                    CtClass cls=ctConstructor.getDeclaringClass();
                    if(cls!=null){
                        CtClass parent=cls;
                        do{
                            parent=parent.getSuperclass();
                        }while (parent!=null&&!parent.getName().equals(AbstractGameAction.class.getName())&&!parent.getName().equals(AbstractPower.class.getName()));
                        if (parent != null && (parent.getName().equals(AbstractGameAction.class.getName()) || !parent.getName().equals(AbstractPower.class.getName()))) {
                            if (ctConstructor.getDeclaringClass().getName().equals(DrawCardAction.class.getName()) || ctConstructor.getDeclaringClass().getName().equals(DrawCardNextTurnPower.class.getName()))
                                res[0] = true;
                        }
                    }
                } catch (NotFoundException ignored) {

                }
            }
        });
        return res[0];
    }

    @Override
    public void triggerOnGlowCheck() {
        if(!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()){
            AbstractCard card=AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size()-1);
            boolean isDrawCard=false;
            if(cardsCanDraw.contains(card.cardID)){
                isDrawCard=true;
            }else{
                try {
                    if(card instanceof YUZUCustomCard){
                        isDrawCard=drawCheck(card,"commonUse")||drawCheck(card,"masterUse");
                    }else{
                        isDrawCard=drawCheck(card,"use");
                    }

                } catch (NotFoundException | CannotCompileException ignored) {
                    canDraw=false;
                    super.triggerOnGlowCheck();
                }
            }

            if(isDrawCard){
                canDraw=true;
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }else{
                canDraw=false;
                super.triggerOnGlowCheck();
            }
        }
    }
}

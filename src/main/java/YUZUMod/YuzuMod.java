package YUZUMod;


import YUZUMod.action.YUZUApplyCriticalRateAction;
import YUZUMod.action.YUZUMasterCardAction;
import YUZUMod.cards.YUZUCustomCard;
import YUZUMod.cards.colorless.YUZUIronBox;
import YUZUMod.cards.options.YUZUForkedIntersectionOption;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import YUZUMod.helper.YUZUPotionTarget;
import YUZUMod.patch.YUZUBlockWordEffectPatch;
import YUZUMod.power.YUZUCriticalHitPower;
import YUZUMod.relic.*;
import YUZUMod.ui.YUZUCriticalIcon;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.CardBorderGlowManager;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.icons.CustomIconHelper;
import com.evacipated.cardcrawl.mod.stslib.patches.CustomTargeting;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;

import java.nio.charset.StandardCharsets;

import static com.megacrit.cardcrawl.core.Settings.language;

@SpireInitializer
public class YuzuMod implements EditCharactersSubscriber , EditCardsSubscriber , EditStringsSubscriber , EditRelicsSubscriber , EditKeywordsSubscriber ,PostInitializeSubscriber,OnCardUseSubscriber{
    public static final Color YUZUColor = new Color(255.0F/255.0F,130.0F/255.0F,135.0F/255.0F,1.0F);

    private static final String YUZU_CHARACTER_BUTTON = ModHelper.makeImgPath("character", "Character_Button");
    private static final String YUZU_CHARACTER_PORTRAIT = ModHelper.makeImgPath("character", "Character_Portrait");
    private static final String YUZU_ATTACK_512 = ModHelper.makeImgPath("512", "bg_attack_512");
    private static final String YUZU_POWER_512 = ModHelper.makeImgPath("512", "bg_power_512");
    private static final String YUZU_SKILL_512 = ModHelper.makeImgPath("512", "bg_skill_512");
    private static final String YUZU_ATTACK_1024 = ModHelper.makeImgPath("1024", "bg_attack_1024");
    private static final String YUZU_POWER_1024 = ModHelper.makeImgPath("1024", "bg_power_1024");
    private static final String YUZU_SKILL_1024 = ModHelper.makeImgPath("1024", "bg_skill_1024");
    private static final String YUZU_BIG_ORB = ModHelper.makeImgPath("512", "card_orb");
    private static final String YUZU_ENERGY_ORB = ModHelper.makeImgPath("1024", "cost_orb");
    private static final String YUZU_SMALL_ORB = ModHelper.makeImgPath("512", "small_orb");

    public YuzuMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(YuzuCharacter.PlayerClass.YUZU_CARD, YUZUColor, YUZUColor, YUZUColor, YUZUColor, YUZUColor, YUZUColor, YUZUColor, YUZU_ATTACK_512, YUZU_SKILL_512, YUZU_POWER_512, YUZU_BIG_ORB, YUZU_ATTACK_1024, YUZU_SKILL_1024, YUZU_POWER_1024, YUZU_ENERGY_ORB, YUZU_SMALL_ORB);
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new YuzuCharacter(CardCrawlGame.playerName),YUZU_CHARACTER_BUTTON,YUZU_CHARACTER_PORTRAIT);
    }


    @Override
    public void receiveEditCards() {
//        BaseMod.addCard(new YUZUStrike());
//        BaseMod.addCard(new YUZUDefend());
//        BaseMod.addCard(new YUZUJobResponsibilities());
//        BaseMod.addCard(new YUZUAnalysis());
//        BaseMod.addCard(new YUZURefraction());
//        BaseMod.addCard(new YUZUBeMercifulToOthers());
//        BaseMod.addCard(new YUZUScorchingExplosive());
//        BaseMod.addCard(new YUZUCamouflage());
//        BaseMod.addCard(new YUZUHighPrecisionOperation());
//        BaseMod.addCard(new YUZUPolarization());
//        BaseMod.addCard(new YUZUColdStorage());
//        BaseMod.addCard(new YUZUDesignShooting());
//        BaseMod.addCard(new YUZUSpreadShooting());
        CustomIconHelper.addCustomIcon(new YUZUCriticalIcon());


        new AutoAdd("BlueArchive_yuzu_Mod").packageFilter(YUZUCustomCard.class)
                .notPackageFilter(YUZUForkedIntersectionOption.class)
                .notPackageFilter("baModDeveloper.cards.deprecatedCard")
                .setDefaultSeen(true).cards();
        BaseMod.addCard(new YUZUIronBox());
    }

    public static void initialize(){
        new YuzuMod();

    }


    @Override
    public void receiveEditStrings() {
        String lang = "ENG";
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
//        } else if(language==GameLanguage.ENG){
//            lang = "ENG";
        } else if (language == Settings.GameLanguage.ZHT) {
            lang = "ZHS";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "YuzuModResources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "YuzuModResources/localization/" + lang + "/character.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "YuzuModResources/localization/" + lang + "/power.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "YuzuModResources/localization/" + lang + "/uistring.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "YuzuModResources/localization/" + lang + "/relic.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "YuzuModResources/localization/" + lang + "/event.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, "YuzuModResources/localization/" + lang + "/potion.json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class, "YuzuModResources/localization/" + lang + "/monster.json");

    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new YUZUSight(),YuzuCharacter.PlayerClass.YUZU_CARD);
        BaseMod.addRelicToCustomPool(new YUZUSightModifiedVersion(),YuzuCharacter.PlayerClass.YUZU_CARD);
        BaseMod.addRelicToCustomPool(new YUZUEnergyGatheringBomb(),YuzuCharacter.PlayerClass.YUZU_CARD);
        BaseMod.addRelicToCustomPool(new YUZUPortableBattery(),YuzuCharacter.PlayerClass.YUZU_CARD);
        BaseMod.addRelic(new YUZURainbowCat(), RelicType.SHARED);
        BaseMod.addRelicToCustomPool(new YUZUAiSight(),YuzuCharacter.PlayerClass.YUZU_CARD);
        BaseMod.addRelic(new YUZUTorch(), RelicType.SHARED);


    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "ENG";
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }else if(language== Settings.GameLanguage.ZHT){
            lang="ZHS";
        }else if(language== Settings.GameLanguage.JPN){
            lang="JPN";
        }
        String json = Gdx.files.internal("YuzuModResources/localization/" + lang + "/keyword.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("yuzu", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receivePostInitialize() {
        CustomTargeting.registerCustomTargeting(YUZUPotionTarget.POTION,new YUZUPotionTarget());

        CardBorderGlowManager.addGlowInfo(new CardBorderGlowManager.GlowInfo() {
            @Override
            public boolean test(AbstractCard abstractCard) {
                return YUZUCustomCard.isMastered(abstractCard)>0;
            }

            @Override
            public Color getColor(AbstractCard abstractCard) {
                return YUZUColor.cpy();
            }

            @Override
            public String glowID() {
                return "YUZU:masterColor";
            }
        });
        CardBorderGlowManager.addGlowInfo(new CardBorderGlowManager.GlowInfo() {
            @Override
            public boolean test(AbstractCard abstractCard) {
                return AbstractDungeon.player.hasPower(YUZUCriticalHitPower.POWER_ID)&&abstractCard.type== AbstractCard.CardType.ATTACK;
            }

            @Override
            public Color getColor(AbstractCard abstractCard) {
                return Color.YELLOW.cpy();
            }

            @Override
            public String glowID() {
                return "YUZU:criticalColor";
            }
        });
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.actionManager.addToBottom(new YUZUMasterCardAction(abstractCard));
                AbstractDungeon.actionManager.addToBottom(new YUZUApplyCriticalRateAction(1));
                this.isDone=true;
            }
        });
        YUZUBlockWordEffectPatch.isCriticalHit=false;

    }
}

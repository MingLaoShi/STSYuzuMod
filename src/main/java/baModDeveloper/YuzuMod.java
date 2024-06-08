package baModDeveloper;


import baModDeveloper.Helper.ModHelper;
import baModDeveloper.cards.*;
import baModDeveloper.cards.colorless.YUZUForkedIntersectionOption;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.relic.YUZUSight;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;

import java.nio.charset.StandardCharsets;

import static com.megacrit.cardcrawl.core.Settings.language;

@SpireInitializer
public class YuzuMod implements EditCharactersSubscriber , EditCardsSubscriber , EditStringsSubscriber , EditRelicsSubscriber , EditKeywordsSubscriber {
    public static final Color YUZUColor = new Color(252.0F / 255.0F, 168.0F / 255.0F, 198.0F / 255.0F, 1.0F);

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
        new AutoAdd("BlueArchive_yuzu_Mod").packageFilter(YUZUCustomCard.class).notPackageFilter(YUZUForkedIntersectionOption.class).setDefaultSeen(true).cards();
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
}

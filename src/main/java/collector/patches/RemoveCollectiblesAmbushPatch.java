package collector.patches;

import collector.CollectorCollection;
import collector.util.CollectibleRemoveEffect;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.chests.BossChest;
import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;
import slimebound.SlimeboundMod;

@SpirePatch(clz = com.megacrit.cardcrawl.screens.DungeonMapScreen.class, method = "open")
public class RemoveCollectiblesAmbushPatch {
    //Surprise! Card remove time.

    @SpirePostfixPatch
    public static void open(DungeonMapScreen __instance, boolean doScrollingAnimation) {
        CollectorCollection.testSize();//Checks if bag of tricks, if so max is 7, otherwise 5.

        SlimeboundMod.logger.info("Map is opening");
        if (CollectorCollection.collection.size() > CollectorCollection.MaxCollectionSize) {
            if (AbstractDungeon.getCurrMapNode() != null && !(AbstractDungeon.getCurrMapNode().room instanceof TreasureRoomBoss)) {//Boss chest room causes weird issues as it is poorly coded.
                SlimeboundMod.logger.info("Passed check, opening remove effect at value of " + (CollectorCollection.collection.size() - CollectorCollection.MaxCollectionSize));
                AbstractDungeon.effectList.add(new CollectibleRemoveEffect(CollectorCollection.collection.size() - CollectorCollection.MaxCollectionSize));
            }
        }
    }

}
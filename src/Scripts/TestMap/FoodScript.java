package Scripts.TestMap;

import java.util.ArrayList;
import java.util.Random;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import GameObject.Frame;
import Level.*;
import Screens.PlayLevelScreen;
import ScriptActions.*;
import Utils.Point;

public class FoodScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasEatenFood", false));
                scriptActions.add(new LockPlayerScriptAction());
                scriptActions.add(new TextboxScriptAction() {{
                    addText("*Otis curiously looks at the apple...*");
                    addText("*He grabs it and eats it!*");
                    addText("*It looks like he enjoyed it?*");

                    addScriptAction(new ScriptAction() {
                        @Override
                        public ScriptState execute() {
                            entity.setMapEntityStatus(MapEntityStatus.INACTIVE);
                            entity.setIsHidden(true);
                            Random random = new Random();
                            Player.gainFriendshipPoints(random.nextInt(3) + 1);
                            int healthToGain = random.nextInt(5) + 1;
                            if(PlayLevelScreen.playerHealth + healthToGain > 50) {
                                PlayLevelScreen.playerHealth = 50;
                            } else {
                                PlayLevelScreen.playerHealth += healthToGain;
                            }
                            
                            return ScriptState.COMPLETED;
                        }
                    });    
                }});
            }});
        }});

       // scriptActions.add(new ChangeFlagScriptAction("hasEatenFood", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
    
}

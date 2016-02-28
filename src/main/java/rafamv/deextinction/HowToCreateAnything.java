package rafamv.deextinction;

public class HowToCreateAnything
{
	/**
	 === Time Periods ===
	 
	 Time Periods are fully implemented, but some of them are inactive due to lack of content. 
	 They represent areas (X-Z) of the world according to a random generated height map created by noise (Simplex Noise);
	 The height created by the noise is checked and it will be inside an interval of a registered period.
	 The interval of each period is set automatically according to the getSpawnWeight() in each period class;  
	 Only active periods will be added to the world, so they must have to be set in the DEDatabaseRegistry;
	 	 
	 === Creatures and Foliage ===
	 
	 Creatures and foliage are divided in branches. Each branch is a possible research branch. For example, foliage can be a tree or bush;
	 A branch is a hashmap of creature names and creature basic information. It has its unique ID so the DEDatabaseRegistry can register it properly;
	 IDs are also important to the tileEntity responsible to deextinct those entities;
	 
	 === Branch creation ===
	 
	 1. Registry:
	 1.1 In the DeDatabaseRegistry class (common.registry package):
	 - Add a unique ID to the branch (public static final byte (NAME_OF_THE_BRANCH)_BRANCH);
	 - Add a HashMap<String, Creature> LIST_CREATURE_(NAME_OF_THE_BRANCH)_BRANCH;
	 - In the addCreatureInBranch(), add the ID checker and add the creature passed in the method to the hashmap (LIST_CREATURE_(NAME_OF_THE_BRANCH)_BRANCH.put(creatureName, creature));
	 
	 1.2. In the Gui (GuiGeneticResearchStation/GuiBotanicalResearchStation):
	 - Create another button saying the (non-extinct) creature of the branch;
	 - Add an action for the button in the actionPerformed() method; In the default part of the first switch: confirm the button id, set the name of the creature (static String inside all creature/foliage class), and call changeGalleryBranch((NAME_OF_THE_BRANCH)_BRANCH) using the new ID;
	 - Inside changeGalleryBranch() checks the branch ID and set the creature list by using this.registerBranch(DEDatabaseRegistry.LIST_CREATURE_(NAME_OF_THE_BRANCH)_BRANCH, this.researchStation.getCreatureProgressList(), this.researchStation.getCreatureSelected());
	 - This will set all the current research from that tileEntity to the next page, so the player can access it;
	 
	 === Creature/Foliage creation ===
	 
	 1. Creation:
	 1.1 Create all needed items for the creature/foliage (fossil, drops, foliage block...); 
	 1.2 In the package common.database, create a new class of the creature/foliage. A creature can be created by the player from another living entity (SyringeCreature) or from an egg (EggCreature);
	 - Set all the variables in the new class. 
	 - GalleryPosition is the position in the Gui. 
	 - Nutrients range from 0 to 1000;
	 - Register the items in the button from the gui, the items/blocks required to the research and the items that are returned;
	 - Register the time period that the creature/foliage is found. Don't forget to set it to active in the Registry class;
	 - Set the previous creatures/foliage required to the research.
	 - Set all the variants of the creature/foliage;
	 - Set the parameters of the growth stages, e.g. health, attack, model scale;
	 1.3 Create the render and model classes. You can use the model animation API that I created (Restricted to this mod ONLY); 
	 1.4 Create the entity class. There are pre-created AI that can be implemented. They can be removed from the entity according to its age by using updateAIForGrowthStage() (check already done entity classes for more info);
	 
	 2. Registry:
	 2.1 In the DEDatabaseRegistry, in the registerCreatures(), add the creature or foliage to be registered.
	 
	 === Debuging ===
	 When you start minecraft, everything that was registered will appear in the console in this sequence:
	 Time Period
	 Creatures: [Creature name, interval height];
	 Foliage: [Foliage name, interval height];
	 
	 Note that the period height is created from a noise function that generates values from -1.0F to 1.0F;
	 An interval is set from the last height to the height shown; Note that there is a correction in the height creation because values close to -1 and 1 are very rare.
	 	 
	 */
}

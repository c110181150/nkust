public class role {
    public static void main(String[] args) {

        skill Archer = new skill(15, 10, 30, "弓箭手", "普攻", "大冰劍", "幻影之舞");
        Archer.Displaydata();
        Archer.displayskill();
        
        System.out.println("------------------------");

        skill Berserker = new skill(20, 5, 15, "狂戰士","普攻","狂暴之力","好戰者");
        Berserker.Displaydata();
        Berserker.displayskill();

        System.out.println("------------------------");

        skill Magician = new skill(10, 30, 40, "魔法師","普攻","地熱","死亡之帽");
        Magician.Displaydata();
        Magician.displayskill();
    }
}

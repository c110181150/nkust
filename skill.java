public class skill extends human {
    String attack;
    String skills;
    String equipment;

    skill(int roleHp, int roleMp, int roleAttackPower, String roleName, String roleAttack, String roleSkills, String roleEquipment) {
        super(roleHp, roleMp, roleAttackPower, roleName);
        attack = roleAttack;
        skills = roleSkills;
        equipment = roleEquipment;
    }

    void displayskill(){
        System.out.println("攻擊:"+attack);
        System.out.println("詠唱:"+skills);
        System.out.println("裝備:"+equipment);
    }
}

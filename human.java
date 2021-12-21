public class human {
    int Hp;
    int Mp;
    int attackPower;
    String name;

    human(int roleHp,int roleMp ,int roleAttckPower ,String roleName){
        Hp=roleHp;
        Mp=roleMp;
        attackPower=roleAttckPower;
        name=roleName;
    }

    void Displaydata(){
        System.out.println(name+"的血量為"+Hp);
        System.out.println(name+"的魔力為"+Mp);
        System.out.println(name+"的攻擊力為"+attackPower);
    }
}
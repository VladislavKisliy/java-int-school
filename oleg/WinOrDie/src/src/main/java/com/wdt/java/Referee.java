/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winordie;

import java.util.List;

/**
 *
 * @author Oleg
 */
public class Referee implements IReferee{
    public GameUser currentUser;
    private GameUser user1;
    private GameUser user2;
    private List<CellState> lastBattleField;
    
    Referee(GameUser inputUser1, GameUser inputUser2){
        user1=inputUser1;
        user2=inputUser2;
        currentUser=user1;
    }
    public void changeUser(){
        if(currentUser==user1){
            currentUser=user2;
        }else{
            currentUser=user1;
        }
    }
    public boolean cheated(List<CellState> battleField){
        int z=0;
        for(int i=0;i<Constants.DIMENTION*Constants.DIMENTION;i++){
            if(battleField.get(i)!=lastBattleField.get(i)){
                if(lastBattleField.get(i).getEntity()!=Constants.SIGN_NULL){return true;}
                z+=1;                    
            }
        }
        if(z!=1){return true;}
        return false;
    }
    public boolean won(List<CellState> battleField){
        int dim=Constants.DIMENTION;
        List<Integer> statuses=AIClass.getVariantsToCheck(dim);
        for(int i=0;i<dim*dim;i+=dim){
            if(battleField.get(statuses.get(i))==battleField.get(statuses.get(i+1)) && battleField.get(statuses.get(i))==battleField.get(statuses.get(i+2))){
                return true;
            }
        }
        return false;
    }
    public boolean hasNextStep(List<CellState> gameField){ //to referee
        for(int i=0;i<gameField.size();i++){
            if(gameField.get(i).getEntity()==Constants.SIGN_NULL){
                return true;
            }
        }
        return false;
    } 
}

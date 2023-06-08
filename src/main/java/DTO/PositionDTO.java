package DTO;

import com.example.weblibrary.model.Employee;
import com.example.weblibrary.model.Position;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class PositionDTO {
    private Integer id;
    private String positionName;

    public static PositionDTO fromPosition(Position position){
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setId(position.getId());
        positionDTO.setPositionName(position.getPositionName());
        return positionDTO;
    }

    public Position toPosition(PositionDTO positionDTO){
        Position position = new Position();
        position.setId(this.getId());
        position.setPositionName(this.getPositionName());
        return position;
    }
}

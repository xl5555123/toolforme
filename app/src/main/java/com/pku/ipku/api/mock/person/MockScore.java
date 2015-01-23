package com.pku.ipku.api.mock.person;

import com.pku.ipku.model.person.dto.ScoreDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pktxq on 15-1-23.
 */
public class MockScore {
    private static List<ScoreDTO> scoreDTOList = new ArrayList<ScoreDTO>(){
        {
            add(new ScoreDTO("1401213000","佛学院","14-15","1","01710122","学科前沿研究讨论班","3","85","必修"));
            add(new ScoreDTO("1401213000","佛学院","14-15","1","01710122","高级系统软件技术","3","85","必修"));
            add(new ScoreDTO("1401213000","佛学院","14-15","1","01710122","软件设计模式","3","85","必修"));
            add(new ScoreDTO("1401213000","佛学院","14-15","1","01710122","网络大数据管理理论和应用","3","85","必修"));
            add(new ScoreDTO("1401213000","佛学院","14-15","1","01710122","研究生科学精神与学科素养","3","85","必修"));
            add(new ScoreDTO("1401213000","佛学院","14-15","1","01710122","自然辩证法概论","3","85","必修"));
            add(new ScoreDTO("1401213000","外国语学院","14-15","1","01710122","研究生英语实用写作","3","85","必修"));

        }
    };

    public static List<ScoreDTO> get(){
        return scoreDTOList;
    }
}

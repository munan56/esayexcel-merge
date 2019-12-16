package ml.munan.boot.demo.exceldemo;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import com.google.common.collect.ListMultimap;
import lombok.Data;
import ml.munan.boot.demo.exceldemo.model.ExportDto;
import ml.munan.boot.demo.exceldemo.model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: munan
 * @Date: 2019/12/16 7:59 下午
 */
@Data
public class MyMerger<T> extends AbstractMergeStrategy {

    private int fistCol;

    private int lastCol;

    private Map<Long, List<ExportDto>> map ;

    public MyMerger(int fistCol, int lastCol, Map<Long, List<ExportDto>> map ) {
        this.fistCol = fistCol;
        this.lastCol = lastCol;
        this.map = map;

    }

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {

        if (cell.getColumnIndex() != 0) {
            return;
        }
        Double aDouble = cell.getNumericCellValue();
        if (!map.containsKey(aDouble.longValue()) || CollectionUtils.isEmpty(map.get(aDouble.longValue())) || map.get(aDouble.longValue()).size() <= 1) {
            return;
        }
        CellRangeAddress cellRangeAddress =
                new CellRangeAddress(cell.getRowIndex(), map.get(aDouble.longValue()).size() + cell.getRowIndex() - 1, fistCol, lastCol);
        sheet.addMergedRegion(cellRangeAddress);
        map.remove(aDouble.longValue());
    }
}

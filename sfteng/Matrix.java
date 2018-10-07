import java.util.*;
public class Matrix
{
    int rows;
    int columns;
    static List<String> head_list;
    double[][] fixed_values = {{1,0,0.2,0,0,0.5},{1,0,0,0.4,0.6,0},{0,1,0.7,0,0,0.8},{0,1,0,0.6,0.8,0}};
    public Matrix(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        

    }


    public double[][] create_matrix()
    {

        
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the headings of each column");
        head_list = new ArrayList<String>();
        for(int i = 0; i < columns; i++)
        {
            System.out.print("column " + i + ". ");
            head_list.add(scan.next());

            System.out.println();
        }
        
        double[][] matrix = new double[rows][columns];
        for(int i = 0; i < rows; i++)
        {
            System.out.println("enter the elements of row " + i);
            for(int j = 0; j < columns; j++)
            {
                // matrix[i][j] = scan.nextFloat();
                matrix[i][j] = fixed_values[i][j];
            }
        }
        return matrix;
    }

    public void calculate_path_probalities()
    {
        double matrix[][] = fixed_values;
        for(int i = 0; i < rows; i++)
        {
            double prob = 1;
            System.out.println("path " + i);
            System.out.print("-> 0");
            for(int j = 0; j < columns; j++)
            {
                if(matrix[i][j] != 0)
                {
                    prob = prob*matrix[i][j];
                    System.out.print("-> " + j);
                }
            }
            System.out.println();
            System.out.println("total probablity of the path is " + prob);
        }
    }


}
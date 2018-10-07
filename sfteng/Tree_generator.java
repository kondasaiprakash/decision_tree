import java.util.Scanner;

public class Tree_generator
{
    Matrix matrix;
    String tree_name;
    Matrix_version_node root_node;
    static Matrix_version_node current_node;


    public Tree_generator(String name)
    {
        this.tree_name = name;
    }

    public void create_tree()
    {
        double[][] matrix_created;
        root_node = new Matrix_version_node(-1,null,1);
        System.out.println("enter rows and columns");
        Scanner scan = new Scanner(System.in);
        matrix = new Matrix(scan.nextInt(),scan.nextInt());
        matrix_created = matrix.create_matrix();
        current_node = root_node;   
        for(int i = 0; i < matrix.rows; i++)
        {
            for(int j = 0; j < matrix.columns; j++)
            {
                if(matrix_created[i][j] != 0)
                {
                    int size = 0;
                    int k = 0;
                    int verify = 0;
                    if(current_node.child_nodes != null)
                    {
                        size = current_node.child_nodes.size();
                        verify = 0;
                        k = 0;
                    }
                    while(k < size)
                    {
                        k++;
                        Matrix_version_node check = current_node.child_nodes.get(size-1);
                        if(check.node_id == j)
                        {
                            current_node = check;
                            verify = 1;
                            break;
                            
                        }
                    }
                    if(verify != 1)
                    {

                    
                        Matrix_version_node node = new Matrix_version_node(j,current_node,matrix_created[i][j]);
                        current_node = node;
                    }


                    
                }
            }
            current_node = root_node;
        }
    }


    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        Tree_generator tree = new Tree_generator(scan.next());
        tree.create_tree();
        // tree.matrix.calculate_path_probalities();
        // tree.root_node.complete_details(tree.root_node);
        // tree.root_node.best_decision(tree.root_node);
        tree.root_node.take_decision(tree.root_node);
        tree.root_node.tree_print(" ", tree.root_node, true);
        tree.root_node.best_decision_second("", "", 0, 1, tree.root_node);
        // System.out.println(path);
        System.out.println(Matrix_version_node.most_best + " is the best path to take");
    }
}
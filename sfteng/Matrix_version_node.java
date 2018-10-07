import java.util.*;


public class Matrix_version_node
{
    Matrix_version_node parent_node;
    int node_id =0;

    static int node_no = 0;
    int node_specific_id;
    List<Matrix_version_node> child_nodes;
    String query;
    double priority;

    public Matrix_version_node(int id,Matrix_version_node parent,double priority)
    {
        node_no++;
        this.node_specific_id = node_no;
        this.node_id = id;
        child_nodes = new ArrayList<Matrix_version_node>();
        System.out.println("node id  :  " + node_id);
        if(id == -1)
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("enter the name of the tree : ");
            this.query = scan.next();
            this.priority = priority;
        }
        else
        {
            this.query = Matrix.head_list.get(id);
            // this.priority = parent.priority * priority;
        }
        this.priority = priority;
        this.parent_node = parent;
        if(parent_node != null)
        {
            // System.out.println("it reached here");
            parent_node.child_nodes.add(this);
        }
        // System.out.println("node title : " + name);

    }
    public void toString(Matrix_version_node node)
    {
        System.out.println( "specific id : " + node.node_specific_id + " priority : "+node.priority +  " column_id : " + node.node_id + " query " + node.query);
    }


    public void take_decision(Matrix_version_node decision)
    {
        if(decision.child_nodes.size() != 0)
        {
            int option = options_printer(decision);
            if(option == 0)
            {
                if(decision.parent_node != null)
                {
                    take_decision(decision.parent_node);
                }
                else
                {
                    take_decision(decision);
                }
            }
            else
            {
                Matrix_version_node choosed = decision.child_nodes.get(option-1);
                take_decision(choosed);
        
            }
        }
        else
        {
            
            System.out.println(decision.query + "\n thank you ");
        }
    }

    

    public int options_printer(Matrix_version_node node)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("0 . move to previous query");
        for(int i = 0; i < node.child_nodes.size(); i++)
        {
            Matrix_version_node x = node.child_nodes.get(i);
            System.out.println((i+1) +  ". " + x.query);
        }
        System.out.println("choose any of the options : ");
        return scan.nextInt();
        

        
    }
    
    public void best_decision(Matrix_version_node dec)
    {
        
        double priority_max = 0;
        // int id = 0;
        Matrix_version_node selected = null;
        for(int i = 0; i < dec.child_nodes.size(); i++)
        {
            if(dec.child_nodes.get(i).priority > priority_max)
            {
                selected = dec.child_nodes.get(i);
                priority_max = selected.priority;
                
            }
        }
        System.out.print(dec.node_specific_id  + dec.priority + " -> ");
        if(selected != null && selected.child_nodes.size() != 0)
        {
            best_decision(selected);
        }
    }

    public void complete_details(Matrix_version_node node)
    {
        node.toString(node);
        for(int i = 0; i < node.child_nodes.size(); i++)
        {
            // node.child_nodes.get(i).toString();
            complete_details(node.child_nodes.get(i));
        }
    }
    static String most_best;
    static double best_priority_check;
    public void best_decision_second(String best_path,String current_path, double best_priority,double current_priority,Matrix_version_node node)
    {
        current_path += "->" + node.node_specific_id;
        // System.out.println(current_path + " : " +  current_priority);
        if(node.priority < 1)
        {
            current_priority *= node.priority;
        }
        if(node.child_nodes.size() == 0)
        {
            // System.out.println(current_priority + "  n,,nnnnnnn,.,,.mm " + best_priority_check);
            if(current_priority > best_priority_check)
            {
                best_priority = current_priority;
                best_priority_check = current_priority;
                best_path = current_path;
                most_best = best_path;
                
            
            }
        }
        for(int i = 0; i < node.child_nodes.size(); i++)
        {
            node.best_decision_second(best_path, current_path, best_priority, current_priority, node.child_nodes.get(i));
        }
        // System.out.println(best_path);
        // return best_path;
        // System.out.println(best_path + "  :  " + best_priority);
        System.out.println(best_path + "  :  " + best_priority);

    }



    public void tree_print(String prefix,Matrix_version_node node,boolean isLast)
    {
        System.out.println(prefix + "|");
        System.out.println(prefix + (isLast ? "|____" : "|----") + node.node_specific_id + ": " + node.query);

        for(int i = 0; i < node.child_nodes.size()-1; i++)
        {
            node.child_nodes.get(i).tree_print(prefix + (isLast ? "     " : "|    "),node.child_nodes.get(i), false);

        }
        if(node.child_nodes.size() > 0)
        {
            node.child_nodes.get(node.child_nodes.size()-1).tree_print(prefix + (isLast ?"     " : "|    "), node.child_nodes.get(node.child_nodes.size()-1), true);
        }

        
    }
}
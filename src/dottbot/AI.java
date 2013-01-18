 package dottbot;

/**
 *
 * @author BEDI
 */
import java.io.*;

public class AI {
    static String[][] matrix=new String[3][3];
    static String[][] tempmatrix=new String[3][3];
    static int max=0; 
    static int rselect=0; 
    static int cselect=0; 
    public static void main(String[] args) {
        try
        {
        	System.out.println();
            int min=4;
            int minr=0;
            int minc=0;
            int[][] difference=new int[2][2];
            int index=0;
            int counter=0;
            String line="";
            BufferedReader br=new BufferedReader(new FileReader("input.txt"));
            while((line=br.readLine())!=null)
            {
                matrix[counter][0]=line.substring(0, 2);
                matrix[counter][1]=line.substring(3, 5);
                matrix[counter][2]=line.substring(6, 8);
                counter++;
            }
            counter=0;
            copy();
            repeat(difference,0);
            copy();
            position pos=checkpos(rselect,cselect);
            if(pos.place==0&&max!=0)
            {
                System.out.println(pos.row+" "+pos.column+" "+"0");
                System.exit(0);
            }
            if(pos.place==1&&max!=0)
            {
                System.out.println(pos.row+" "+pos.column+" "+"1");
                System.exit(0);
            }
            for(int i=0;i<2;i++)
            {
                for(int j=0;j<2;j++)
                {
                    max=0;
                    if(!tempmatrix[i][j].substring(0, 1).equals("1"))
                    {
                        tempmatrix[i][j]="1"+tempmatrix[i][j].substring(1, 2);
                        repeat(difference,0);
                        if(min>max)
                        {
                            min=max;
                            minr=i;
                            minc=j;
                            counter=0;
                        }
                        copy();
                    }
                    max=0;
                    if(!tempmatrix[i][j].substring(1, 2).equals("1"))
                    {
                        tempmatrix[i][j]=tempmatrix[i][j].substring(0, 1)+"1";
                        repeat(difference,0);
                        if(min>max)
                        {
                            min=max;
                            minr=i;
                            minc=j;
                            counter=1;
                        }
                        copy();
                    }
                    max=0;
                    if(!tempmatrix[i+1][j].substring(0, 1).equals("1"))
                    {
                        tempmatrix[i+1][j]="1"+tempmatrix[i+1][j].substring(1, 2);
                        repeat(difference,0);
                        if(min>max)
                        {
                            min=max;
                            minr=i+1;
                            minc=j;
                            counter=0;
                        }
                        copy();
                    }
                    max=0;
                    if(!tempmatrix[i][j+1].substring(1, 2).equals("1"))
                    {
                        tempmatrix[i][j+1]=tempmatrix[i][j+1].substring(0, 1)+"1";
                        repeat(difference,0);
                        if(min>max)
                        {
                            min=max;
                            minr=i;
                            minc=j+1;
                            counter=1;
                        }
                        copy();
                    }
                }
            }
            System.out.println(minr+" "+minc+" "+counter);
        }catch(Exception e)
        {
            System.out.println(e);
            
        }
    }
    static public void repeat(int [][]difference,int count)
    {
        position pos;
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                difference[i][j]=check(i,j);
            }
        }
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                if(difference[i][j]==1)
                {
                    count++;
                    pos=checkpos(i,j);
                    if(pos.place==0)
                    {
                        tempmatrix[pos.row][pos.column]="1"+tempmatrix[pos.row][pos.column].substring(1, 2);
                    }
                    else
                    {
                        tempmatrix[pos.row][pos.column]=tempmatrix[pos.row][pos.column].substring(0, 1)+"1";
                    }
                    if(count>max)
                    {
                        max=count;
                    }
                    repeat(difference,count);
                    if(max>=count)
                    {
                        rselect=i;
                        cselect=j;
                    }
                }
            }
        }
        return;
    }
    static public int check(int rindex,int cindex)
    {
        int diff=0;
        String value="";
        if(!tempmatrix[rindex][cindex].substring(0, 1).equals("1"))
            diff++;
        if(!tempmatrix[rindex][cindex].substring(1, 2).equals("1"))
            diff++;
        if(!tempmatrix[rindex+1][cindex].substring(0, 1).equals("1"))
            diff++;
        if(!tempmatrix[rindex][cindex+1].substring(1, 2).equals("1"))
            diff++;
        return diff;
    }
    static public position checkpos(int rindex,int cindex)
    {
        position pos=null;
        if(!tempmatrix[rindex][cindex].substring(0, 1).equals("1"))
        {
            pos=new position(rindex,cindex,0);
        }
        else if(!tempmatrix[rindex][cindex].substring(1, 2).equals("1"))
        {
            pos=new position(rindex,cindex,1);
            
        }
        else if(!tempmatrix[rindex+1][cindex].substring(0, 1).equals("1"))
        {
            pos=new position(rindex+1,cindex,0);
        }
        else if(!tempmatrix[rindex][cindex+1].substring(1, 2).equals("1"))
        {
            pos=new position(rindex,cindex+1,1);
        }
        return pos;
    }
    static public void copy()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                tempmatrix[i][j]=matrix[i][j];
            }
        }
    }
}
class position
{
    int row;
    int column;
    int place;
    position(int r,int c,int p)
    {
        row=r;
        column=c;
        place=p;
    }
}

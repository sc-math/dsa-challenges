package leetcode.tree;

/*
226. Invert Binary Tree

Pattern: DFS / Recursion

Time: O(n)
Space: O(h)

Idea:
- para cada nó, trocar left e right
- aplicar recursão nos dois filhos

Key trick:
inverter a árvore durante a travessia DFS
*/

public class LC0226 {
    public TreeNode invertTree(TreeNode root) {

        if (root == null)
            return null;

        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}

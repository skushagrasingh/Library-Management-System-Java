import java.util.Scanner;

class Book {
    int id;
    String title;
    String author;
    Book left, right;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        left = right = null;
    }
}

class BST {
    private Book root;

    BST() {
        root = null;
    }

    private Book insert(Book node, int id, String title, String author) {
        if (node == null) return new Book(id, title, author);
        if (id < node.id) node.left = insert(node.left, id, title, author);
        else if (id > node.id) node.right = insert(node.right, id, title, author);
        return node;
    }

    void insert(int id, String title, String author) {
        root = insert(root, id, title, author);
    }

    private Book search(Book node, int id) {
        if (node == null || node.id == id) return node;
        if (id < node.id) return search(node.left, id);
        return search(node.right, id);
    }

    void search(int id) {
        Book b = search(root, id);
        if (b != null) System.out.println("Book found: ID: " + b.id + ", Title: " + b.title + ", Author: " + b.author);
        else System.out.println("Book not found.");
    }

    private void inorder(Book node) {
        if (node != null) {
            inorder(node.left);
            System.out.println("ID: " + node.id + ", Title: " + node.title + ", Author: " + node.author);
            inorder(node.right);
        }
    }

    void display() {
        inorder(root);
    }

    private Book minValue(Book node) {
        while (node != null && node.left != null) node = node.left;
        return node;
    }

    private Book deleteNode(Book node, int id) {
        if (node == null) return null;
        if (id < node.id) node.left = deleteNode(node.left, id);
        else if (id > node.id) node.right = deleteNode(node.right, id);
        else {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            Book temp = minValue(node.right);
            node.id = temp.id;
            node.title = temp.title;
            node.author = temp.author;
            node.right = deleteNode(node.right, temp.id);
        }
        return node;
    }

    void deleteBook(int id) {
        root = deleteNode(root, id);
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST lib = new BST();

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Display Books");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter title: ");
                    String t = sc.nextLine();
                    System.out.print("Enter author: ");
                    String a = sc.nextLine();
                    lib.insert(id, t, a);
                    break;
                case 2:
                    System.out.print("Enter book ID to search: ");
                    id = sc.nextInt();
                    lib.search(id);
                    break;
                case 3:
                    lib.display();
                    break;
                case 4:
                    System.out.print("Enter book ID to delete: ");
                    id = sc.nextInt();
                    lib.deleteBook(id);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

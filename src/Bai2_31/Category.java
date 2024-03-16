package Bai2_31;

import java.util.Objects;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Category {
	@Override
	public int hashCode() {
		return Objects.hash(showSub, id, name, parent_Category, questions, sub_Categories);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Category other = (Category) obj;
		return showSub == other.showSub && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(parent_Category, other.parent_Category) && Objects.equals(questions, other.questions)
				&& Objects.equals(sub_Categories, other.sub_Categories);
	}

	private StringProperty id;
	private StringProperty name;
	private ObservableList<Question> questions;
	private ObservableList<Category> sub_Categories;
	private Category parent_Category;
	private BooleanProperty showSub;

	public Category(String name) {
		this.name = new SimpleStringProperty(name);
		sub_Categories = FXCollections.observableArrayList();
		questions = FXCollections.observableArrayList();
		showSub = new SimpleBooleanProperty(false);
	}

	public Category(String name, ObservableList<Question> questions) {
		this.questions = questions;
		this.name = new SimpleStringProperty(name);
		sub_Categories = FXCollections.observableArrayList();
		showSub = new SimpleBooleanProperty(false);
	}

	public Category(String name, Category parent) {
		this.name = new SimpleStringProperty(name);
		this.parent_Category = parent;
		parent.addSub_Category(this);
		sub_Categories = FXCollections.observableArrayList();
		questions = FXCollections.observableArrayList();
		showSub = new SimpleBooleanProperty(false);
	}

	public Category(String id, String name, Category parent) {
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.parent_Category = parent;
		parent.addSub_Category(this);
		sub_Categories = FXCollections.observableArrayList();
		questions = FXCollections.observableArrayList();
		showSub = new SimpleBooleanProperty(false);
	}

	public ObservableList<Question> getQuestions() {
		return questions;
	}

	public ObservableList<Category> getSub_Category() {
		return sub_Categories;
	}

	public String getName() {
		return name.get();

	}

	public void setName(String name) {
		this.name.set(name);
	}

	// Set Sub Categories
	public void setSub_Categories(ObservableList<Category> subCategories) {
		this.sub_Categories = subCategories;
	}

	// Add Sub Category
	public void addSub_Category(Category sub_Category) {
		this.sub_Categories.add(sub_Category);
	}

	// Add question
	public void addQuestion(Question question) {
		questions.add(question);
	}

	// Set Parent
	public void setParent(Category cate) {
		cate.addSub_Category(this);
		this.parent_Category = cate;
	}

	public int sizeQuestions() {
		return questions.size();
	}

	@Override
	public String toString() {
		Integer size = questions.size();
		String str = name.get();
		if (showSub.get()) {
			size += sizeQuestions_also_subcategory(sub_Categories);
		}
		if (size > 0) {
			str += " (" + String.valueOf(size) + ") ";
		}
		return str;
	}

	public int sizeQuestions_also_subcategory(ObservableList<Category> sub) {
		if (sub.size() == 0)
			return 0;
		int res = 0;
		for (Category cate : sub) {
			int t = sizeQuestions_also_subcategory(cate.getSub_Category());
			int z = cate.sizeQuestions();
			res += t + z;
		}
		return res;
	}

	public void setShowSub(boolean b) {
		showSub.set(b);
	}

	public boolean getShowSub() {
		return showSub.get();
	}

	public ObservableList<Question> getQuestions_also_subCategory(Category cate) {
		ObservableList<Question> all = FXCollections.observableArrayList(cate.getQuestions());
		for (Category c : cate.getSub_Category()) {
			all.addAll(c.getQuestions_also_subCategory(c));
		}
		return all;
	}
}

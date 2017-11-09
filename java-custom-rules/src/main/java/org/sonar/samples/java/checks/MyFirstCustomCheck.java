package org.sonar.samples.java.checks;

import java.util.List;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import com.google.common.collect.ImmutableList;

@Rule(key = "MyFirstCustomCheck", name = "Return type and parameter of a method should not be the same", description = "return description", priority = Priority.CRITICAL, tags = {
		"bug" })
public class MyFirstCustomCheck extends IssuableSubscriptionVisitor {

	@Override
	public List<Kind> nodesToVisit() {
		// TODO Auto-generated method stub
		return ImmutableList.of(Kind.METHOD);
	}

	@Override
	public void visitNode(Tree tree) {
		MethodTree method = (MethodTree) tree;
		if (method.parameters().size() == 1) {
			MethodSymbol ms = method.symbol();
			Type firstParmaeterType = ms.parameterTypes().get(0);
			Type returnType = ms.returnType().type();
			if (returnType.is(firstParmaeterType.fullyQualifiedName())) {
				reportIssue(method.simpleName(), "Do Not Do This");
			}
		}
	}
}

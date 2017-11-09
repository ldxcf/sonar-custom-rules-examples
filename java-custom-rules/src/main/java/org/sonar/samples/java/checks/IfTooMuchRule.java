package org.sonar.samples.java.checks;
import java.util.List;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import com.google.common.collect.ImmutableList;
@Rule(key="IfTooMuchRule",
name="IF LEVEL IS HIGH",
description="if level >5,level is too high",
		  priority=Priority.CRITICAL,
		  tags={"bug"})
public class IfTooMuchRule extends IssuableSubscriptionVisitor{
	@Override
	public List<Kind> nodesToVisit() {
		 return ImmutableList.of(Kind.IF_STATEMENT);
	}
	@Override
	public void visitNode(Tree tree){
		int level = 1;	
		Tree parentTree = tree.parent();
		while(!parentTree.is(Kind.METHOD)){
			if(parentTree.is(Kind.IF_STATEMENT)){
				level++;
			}
			parentTree = parentTree.parent();
		}
		if(level>5){
			reportIssue(tree,"if level >5,level is too high");
		}
	}
}

/*
* generated by Xtext
*/
grammar InternalEntities;

options {
	superClass=AbstractInternalContentAssistParser;
	
}

@lexer::header {
package org.example.entities.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package org.example.entities.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.example.entities.services.EntitiesGrammarAccess;

}

@parser::members {
 
 	private EntitiesGrammarAccess grammarAccess;
 	
    public void setGrammarAccess(EntitiesGrammarAccess grammarAccess) {
    	this.grammarAccess = grammarAccess;
    }
    
    @Override
    protected Grammar getGrammar() {
    	return grammarAccess.getGrammar();
    }
    
    @Override
    protected String getValueForTokenName(String tokenName) {
    	return tokenName;
    }

}




// Entry rule entryRuleModel
entryRuleModel 
:
{ before(grammarAccess.getModelRule()); }
	 ruleModel
{ after(grammarAccess.getModelRule()); } 
	 EOF 
;

// Rule Model
ruleModel
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getModelAccess().getEntitiesAssignment()); }
(rule__Model__EntitiesAssignment)*
{ after(grammarAccess.getModelAccess().getEntitiesAssignment()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleEntity
entryRuleEntity 
:
{ before(grammarAccess.getEntityRule()); }
	 ruleEntity
{ after(grammarAccess.getEntityRule()); } 
	 EOF 
;

// Rule Entity
ruleEntity
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getEntityAccess().getGroup()); }
(rule__Entity__Group__0)
{ after(grammarAccess.getEntityAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleAttribute
entryRuleAttribute 
:
{ before(grammarAccess.getAttributeRule()); }
	 ruleAttribute
{ after(grammarAccess.getAttributeRule()); } 
	 EOF 
;

// Rule Attribute
ruleAttribute
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getAttributeAccess().getGroup()); }
(rule__Attribute__Group__0)
{ after(grammarAccess.getAttributeAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleAttributeType
entryRuleAttributeType 
:
{ before(grammarAccess.getAttributeTypeRule()); }
	 ruleAttributeType
{ after(grammarAccess.getAttributeTypeRule()); } 
	 EOF 
;

// Rule AttributeType
ruleAttributeType
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getAttributeTypeAccess().getGroup()); }
(rule__AttributeType__Group__0)
{ after(grammarAccess.getAttributeTypeAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleElementType
entryRuleElementType 
:
{ before(grammarAccess.getElementTypeRule()); }
	 ruleElementType
{ after(grammarAccess.getElementTypeRule()); } 
	 EOF 
;

// Rule ElementType
ruleElementType
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getElementTypeAccess().getAlternatives()); }
(rule__ElementType__Alternatives)
{ after(grammarAccess.getElementTypeAccess().getAlternatives()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleBasicType
entryRuleBasicType 
:
{ before(grammarAccess.getBasicTypeRule()); }
	 ruleBasicType
{ after(grammarAccess.getBasicTypeRule()); } 
	 EOF 
;

// Rule BasicType
ruleBasicType
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getBasicTypeAccess().getTypeNameAssignment()); }
(rule__BasicType__TypeNameAssignment)
{ after(grammarAccess.getBasicTypeAccess().getTypeNameAssignment()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleEntityType
entryRuleEntityType 
:
{ before(grammarAccess.getEntityTypeRule()); }
	 ruleEntityType
{ after(grammarAccess.getEntityTypeRule()); } 
	 EOF 
;

// Rule EntityType
ruleEntityType
    @init {
		int stackSize = keepStackSize();
    }
	:
(
{ before(grammarAccess.getEntityTypeAccess().getEntityAssignment()); }
(rule__EntityType__EntityAssignment)
{ after(grammarAccess.getEntityTypeAccess().getEntityAssignment()); }
)

;
finally {
	restoreStackSize(stackSize);
}




rule__ElementType__Alternatives
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getElementTypeAccess().getBasicTypeParserRuleCall_0()); }
	ruleBasicType
{ after(grammarAccess.getElementTypeAccess().getBasicTypeParserRuleCall_0()); }
)

    |(
{ before(grammarAccess.getElementTypeAccess().getEntityTypeParserRuleCall_1()); }
	ruleEntityType
{ after(grammarAccess.getElementTypeAccess().getEntityTypeParserRuleCall_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__BasicType__TypeNameAlternatives_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getBasicTypeAccess().getTypeNameStringKeyword_0_0()); }

	'string' 

{ after(grammarAccess.getBasicTypeAccess().getTypeNameStringKeyword_0_0()); }
)

    |(
{ before(grammarAccess.getBasicTypeAccess().getTypeNameIntKeyword_0_1()); }

	'int' 

{ after(grammarAccess.getBasicTypeAccess().getTypeNameIntKeyword_0_1()); }
)

    |(
{ before(grammarAccess.getBasicTypeAccess().getTypeNameBooleanKeyword_0_2()); }

	'boolean' 

{ after(grammarAccess.getBasicTypeAccess().getTypeNameBooleanKeyword_0_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}



rule__Entity__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Entity__Group__0__Impl
	rule__Entity__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Entity__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityAccess().getEntityKeyword_0()); }

	'entity' 

{ after(grammarAccess.getEntityAccess().getEntityKeyword_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Entity__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Entity__Group__1__Impl
	rule__Entity__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Entity__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityAccess().getNameAssignment_1()); }
(rule__Entity__NameAssignment_1)
{ after(grammarAccess.getEntityAccess().getNameAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Entity__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Entity__Group__2__Impl
	rule__Entity__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Entity__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityAccess().getGroup_2()); }
(rule__Entity__Group_2__0)?
{ after(grammarAccess.getEntityAccess().getGroup_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Entity__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Entity__Group__3__Impl
	rule__Entity__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Entity__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_3()); }

	'{' 

{ after(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Entity__Group__4
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Entity__Group__4__Impl
	rule__Entity__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__Entity__Group__4__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityAccess().getAttributesAssignment_4()); }
(rule__Entity__AttributesAssignment_4)*
{ after(grammarAccess.getEntityAccess().getAttributesAssignment_4()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Entity__Group__5
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Entity__Group__5__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Entity__Group__5__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5()); }

	'}' 

{ after(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5()); }
)

;
finally {
	restoreStackSize(stackSize);
}














rule__Entity__Group_2__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Entity__Group_2__0__Impl
	rule__Entity__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Entity__Group_2__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityAccess().getExtendsKeyword_2_0()); }

	'extends' 

{ after(grammarAccess.getEntityAccess().getExtendsKeyword_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Entity__Group_2__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Entity__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Entity__Group_2__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityAccess().getSuperTypeAssignment_2_1()); }
(rule__Entity__SuperTypeAssignment_2_1)
{ after(grammarAccess.getEntityAccess().getSuperTypeAssignment_2_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__Attribute__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Attribute__Group__0__Impl
	rule__Attribute__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Attribute__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeAccess().getTypeAssignment_0()); }
(rule__Attribute__TypeAssignment_0)
{ after(grammarAccess.getAttributeAccess().getTypeAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Attribute__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Attribute__Group__1__Impl
	rule__Attribute__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Attribute__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeAccess().getNameAssignment_1()); }
(rule__Attribute__NameAssignment_1)
{ after(grammarAccess.getAttributeAccess().getNameAssignment_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Attribute__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Attribute__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Attribute__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeAccess().getSemicolonKeyword_2()); }

	';' 

{ after(grammarAccess.getAttributeAccess().getSemicolonKeyword_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}








rule__AttributeType__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__AttributeType__Group__0__Impl
	rule__AttributeType__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AttributeType__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeTypeAccess().getElementTypeAssignment_0()); }
(rule__AttributeType__ElementTypeAssignment_0)
{ after(grammarAccess.getAttributeTypeAccess().getElementTypeAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__AttributeType__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__AttributeType__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AttributeType__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeTypeAccess().getGroup_1()); }
(rule__AttributeType__Group_1__0)?
{ after(grammarAccess.getAttributeTypeAccess().getGroup_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__AttributeType__Group_1__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__AttributeType__Group_1__0__Impl
	rule__AttributeType__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__AttributeType__Group_1__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeTypeAccess().getArrayAssignment_1_0()); }
(rule__AttributeType__ArrayAssignment_1_0)
{ after(grammarAccess.getAttributeTypeAccess().getArrayAssignment_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__AttributeType__Group_1__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__AttributeType__Group_1__1__Impl
	rule__AttributeType__Group_1__2
;
finally {
	restoreStackSize(stackSize);
}

rule__AttributeType__Group_1__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeTypeAccess().getLenghtAssignment_1_1()); }
(rule__AttributeType__LenghtAssignment_1_1)?
{ after(grammarAccess.getAttributeTypeAccess().getLenghtAssignment_1_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__AttributeType__Group_1__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__AttributeType__Group_1__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__AttributeType__Group_1__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeTypeAccess().getRightSquareBracketKeyword_1_2()); }

	']' 

{ after(grammarAccess.getAttributeTypeAccess().getRightSquareBracketKeyword_1_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}









rule__Model__EntitiesAssignment
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getModelAccess().getEntitiesEntityParserRuleCall_0()); }
	ruleEntity{ after(grammarAccess.getModelAccess().getEntitiesEntityParserRuleCall_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Entity__NameAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_1_0()); }
	RULE_ID{ after(grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Entity__SuperTypeAssignment_2_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityAccess().getSuperTypeEntityCrossReference_2_1_0()); }
(
{ before(grammarAccess.getEntityAccess().getSuperTypeEntityIDTerminalRuleCall_2_1_0_1()); }
	RULE_ID{ after(grammarAccess.getEntityAccess().getSuperTypeEntityIDTerminalRuleCall_2_1_0_1()); }
)
{ after(grammarAccess.getEntityAccess().getSuperTypeEntityCrossReference_2_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Entity__AttributesAssignment_4
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityAccess().getAttributesAttributeParserRuleCall_4_0()); }
	ruleAttribute{ after(grammarAccess.getEntityAccess().getAttributesAttributeParserRuleCall_4_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Attribute__TypeAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeAccess().getTypeAttributeTypeParserRuleCall_0_0()); }
	ruleAttributeType{ after(grammarAccess.getAttributeAccess().getTypeAttributeTypeParserRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Attribute__NameAssignment_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeAccess().getNameIDTerminalRuleCall_1_0()); }
	RULE_ID{ after(grammarAccess.getAttributeAccess().getNameIDTerminalRuleCall_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__AttributeType__ElementTypeAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeTypeAccess().getElementTypeElementTypeParserRuleCall_0_0()); }
	ruleElementType{ after(grammarAccess.getAttributeTypeAccess().getElementTypeElementTypeParserRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__AttributeType__ArrayAssignment_1_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeTypeAccess().getArrayLeftSquareBracketKeyword_1_0_0()); }
(
{ before(grammarAccess.getAttributeTypeAccess().getArrayLeftSquareBracketKeyword_1_0_0()); }

	'[' 

{ after(grammarAccess.getAttributeTypeAccess().getArrayLeftSquareBracketKeyword_1_0_0()); }
)

{ after(grammarAccess.getAttributeTypeAccess().getArrayLeftSquareBracketKeyword_1_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__AttributeType__LenghtAssignment_1_1
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getAttributeTypeAccess().getLenghtINTTerminalRuleCall_1_1_0()); }
	RULE_INT{ after(grammarAccess.getAttributeTypeAccess().getLenghtINTTerminalRuleCall_1_1_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__BasicType__TypeNameAssignment
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getBasicTypeAccess().getTypeNameAlternatives_0()); }
(rule__BasicType__TypeNameAlternatives_0)
{ after(grammarAccess.getBasicTypeAccess().getTypeNameAlternatives_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__EntityType__EntityAssignment
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getEntityTypeAccess().getEntityEntityCrossReference_0()); }
(
{ before(grammarAccess.getEntityTypeAccess().getEntityEntityIDTerminalRuleCall_0_1()); }
	RULE_ID{ after(grammarAccess.getEntityTypeAccess().getEntityEntityIDTerminalRuleCall_0_1()); }
)
{ after(grammarAccess.getEntityTypeAccess().getEntityEntityCrossReference_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


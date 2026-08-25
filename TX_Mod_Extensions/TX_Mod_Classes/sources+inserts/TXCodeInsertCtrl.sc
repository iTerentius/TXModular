// Copyright (C) 2009  Paul Miller. This file is part of TX Modular system distributed under the terms of the GNU General Public License (see file LICENSE).

TXCodeInsertCtrl : TXModuleBase {

	classvar <>classData;

	var defaultFunctionString, emptyFunctionString, validFunctionString, userFunctionString;
	var userFuncCompileStatus, userFuncCompileStatusView, displayOption;

	*initClass{
		//	set class specific variables
		classData = ();
		classData.arrInstances = [];
		classData.defaultName = "Code Insert C";
		classData.moduleRate = "control";
		classData.moduleType = "insert";
		classData.noInChannels = 1;
		classData.arrCtlSCInBusSpecs = [
			["Modify 1", 1, "modChange1", 0],
			["Modify 2", 1, "modChange2", 0],
			["Modify 3", 1, "modChange3", 0],
			["Modify 4", 1, "modChange4", 0],
			["Modify 5", 1, "modChange5", 0],
			["Modify 6", 1, "modChange6", 0],
			["Modify 7", 1, "modChange7", 0],
			["Modify 8", 1, "modChange8", 0],
			["Modify 9", 1, "modChange9", 0],
			["Modify 10", 1, "modChange10", 0],
			["Modify 11", 1, "modChange11", 0],
			["Modify 12", 1, "modChange12", 0],
			["Modify 13", 1, "modChange13", 0],
			["Modify 14", 1, "modChange14", 0],
			["Modify 15", 1, "modChange15", 0],
			["Modify 16", 1, "modChange16", 0],
		];
		classData.noOutChannels = 1;
		classData.arrOutBusSpecs = [
			["Out", [0]]
		];
		classData.guiWidth = 800;
	}

	*new{ arg argInstName;
		^super.new.init(argInstName);
	}

	init {arg argInstName;
		//	set  class specific instance variables
		validFunctionString = userFunctionString = defaultFunctionString = "// Example code
		{arg inSignal, mod1, mod2, mod3, mod4;
		inSignal * SinOsc.kr(0.1 + SinOsc.kr(mod1, 0,mod1), 0, 0.5,0.5); }";
		emptyFunctionString = "{DC.kr(0.0)}";
		displayOption = "1-4";
		arrSynthArgSpecs = [
			["in", 0, 0],
			["out", 0, 0],
			["change1", 0, defLagTime],
			["change1Min", 0, defLagTime],
			["change1Max", 1, defLagTime],
			["change2", 0, defLagTime],
			["change2Min", 0, defLagTime],
			["change2Max", 1, defLagTime],
			["change3", 0, defLagTime],
			["change3Min", 0, defLagTime],
			["change3Max", 1, defLagTime],
			["change4", 0, defLagTime],
			["change4Min", 0, defLagTime],
			["change4Max", 1, defLagTime],
			["change5", 0, defLagTime],
			["change5Min", 0, defLagTime],
			["change5Max", 1, defLagTime],
			["change6", 0, defLagTime],
			["change6Min", 0, defLagTime],
			["change6Max", 1, defLagTime],
			["change7", 0, defLagTime],
			["change7Min", 0, defLagTime],
			["change7Max", 1, defLagTime],
			["change8", 0, defLagTime],
			["change8Min", 0, defLagTime],
			["change8Max", 1, defLagTime],
			["change9", 0, defLagTime],
			["change9Min", 0, defLagTime],
			["change9Max", 1, defLagTime],
			["change10", 0, defLagTime],
			["change10Min", 0, defLagTime],
			["change10Max", 1, defLagTime],
			["change11", 0, defLagTime],
			["change11Min", 0, defLagTime],
			["change11Max", 1, defLagTime],
			["change12", 0, defLagTime],
			["change12Min", 0, defLagTime],
			["change12Max", 1, defLagTime],
			["change13", 0, defLagTime],
			["change13Min", 0, defLagTime],
			["change13Max", 1, defLagTime],
			["change14", 0, defLagTime],
			["change14Min", 0, defLagTime],
			["change14Max", 1, defLagTime],
			["change15", 0, defLagTime],
			["change15Min", 0, defLagTime],
			["change15Max", 1, defLagTime],
			["change16", 0, defLagTime],
			["change16Min", 0, defLagTime],
			["change16Max", 1, defLagTime],
			["modChange1", 0, defLagTime],
			["modChange2", 0, defLagTime],
			["modChange3", 0, defLagTime],
			["modChange4", 0, defLagTime],
			["modChange5", 0, defLagTime],
			["modChange6", 0, defLagTime],
			["modChange7", 0, defLagTime],
			["modChange8", 0, defLagTime],
			["modChange9", 0, defLagTime],
			["modChange10", 0, defLagTime],
			["modChange11", 0, defLagTime],
			["modChange12", 0, defLagTime],
			["modChange13", 0, defLagTime],
			["modChange14", 0, defLagTime],
			["modChange15", 0, defLagTime],
			["modChange16", 0, defLagTime],
		];
		synthDefFunc = {
			arg in, out, change1, change1Min, change1Max, change2, change2Min, change2Max,
			change3, change3Min, change3Max, change4, change4Min, change4Max,
			change5, change5Min, change5Max, change6, change6Min, change6Max,
			change7, change7Min, change7Max, change8, change8Min, change8Max,
			change9, change9Min, change9Max, change10, change10Min, change10Max,
			change11, change11Min, change11Max, change12, change12Min, change12Max,
			change13, change13Min, change13Max, change14, change14Min, change14Max,
			change15, change15Min, change15Max, change16, change16Min, change16Max,
			modChange1=0, modChange2=0, modChange3=0, modChange4=0, modChange5=0, modChange6=0, modChange7=0, modChange8=0,
			modChange9=0, modChange10=0, modChange11=0, modChange12=0, modChange13=0, modChange14=0, modChange15=0, modChange16=0;
			var inSignal, outChange1, outChange2, outChange3, outChange4, outChange5, outChange6, outChange7, outChange8,
			outChange9, outChange10, outChange11, outChange12, outChange13, outChange14, outChange15, outChange16;
			inSignal = TXClean.kr(In.kr(in,1));
			outChange1 = change1Min + ((change1Max - change1Min) * (change1 + modChange1).max(0).min(1));
			outChange2 = change2Min + ((change2Max - change2Min) * (change2 + modChange2).max(0).min(1));
			outChange3 = change3Min + ((change3Max - change3Min) * (change3 + modChange3).max(0).min(1));
			outChange4 = change4Min + ((change4Max - change4Min) * (change4 + modChange4).max(0).min(1));
			outChange5 = change5Min + ((change5Max - change5Min) * (change5 + modChange5).max(0).min(1));
			outChange6 = change6Min + ((change6Max - change6Min) * (change6 + modChange6).max(0).min(1));
			outChange7 = change7Min + ((change7Max - change7Min) * (change7 + modChange7).max(0).min(1));
			outChange8 = change8Min + ((change8Max - change8Min) * (change8 + modChange8).max(0).min(1));
			outChange9 = change9Min + ((change9Max - change9Min) * (change9 + modChange9).max(0).min(1));
			outChange10 = change10Min + ((change10Max - change10Min) * (change10 + modChange10).max(0).min(1));
			outChange11 = change11Min + ((change11Max - change11Min) * (change11 + modChange11).max(0).min(1));
			outChange12 = change12Min + ((change12Max - change12Min) * (change12 + modChange12).max(0).min(1));
			outChange13 = change13Min + ((change13Max - change13Min) * (change13 + modChange13).max(0).min(1));
			outChange14 = change14Min + ((change14Max - change14Min) * (change14 + modChange14).max(0).min(1));
			outChange15 = change15Min + ((change15Max - change15Min) * (change15 + modChange15).max(0).min(1));
			outChange16 = change16Min + ((change16Max - change16Min) * (change16 + modChange16).max(0).min(1));
			// use TXClean to stop blowups
			Out.kr(out, TXClean.kr(
				validFunctionString.compile.value.value(
					inSignal,
					outChange1, outChange2, outChange3, outChange4,
					outChange5, outChange6, outChange7, outChange8,
					outChange9, outChange10, outChange11, outChange12,
					outChange13, outChange14, outChange15, outChange16
				)
			));
		};

		this.buildGuiSpecArray;
		arrActionSpecs = this.buildActionSpecs(guiSpecArray);
		//	use base class initialise
		this.baseInit(this, argInstName);
		//	load the synthdef and create the synth
		this.loadAndMakeSynth;
	}

	buildGuiSpecArray {
		guiSpecArray = [
			["ActionButton", "1-4", {displayOption = "1-4";
				this.buildGuiSpecArray; system.showView;}, 130,
			TXColor.white, this.getButtonColour(displayOption == "1-4")],
			["Spacer", 3],
			["ActionButton", "5-8", {displayOption = "5-8";
				this.buildGuiSpecArray; system.showView;}, 130,
			TXColor.white, this.getButtonColour(displayOption == "5-8")],
			["Spacer", 3],
			["ActionButton", "9-12", {displayOption = "9-12";
				this.buildGuiSpecArray; system.showView;}, 130,
			TXColor.white, this.getButtonColour(displayOption == "9-12")],
			["Spacer", 3],
			["ActionButton", "13-16", {displayOption = "13-16";
				this.buildGuiSpecArray; system.showView;}, 130,
			TXColor.white, this.getButtonColour(displayOption == "13-16")],
			["Spacer", 2],
			["DividingLine"],
			["SpacerLine", 2],
			["TextViewCompile", {userFunctionString}, {arg argText; this.evaluate(argText);}, 730, 246, "Store & compile code"],
			["TXStaticText", "Status", {userFuncCompileStatus}, {arg view; userFuncCompileStatusView = view.textView}, 400, 50],
			["SpacerLine", 2],
		];

		if (displayOption == "1-4", {
			guiSpecArray = guiSpecArray ++[
				["TXMinMaxSliderSplit", "Modify 1", \unipolar, "change1", "change1Min", "change1Max"],
				["TXMinMaxSliderSplit", "Modify 2", \unipolar, "change2", "change2Min", "change2Max"],
				["TXMinMaxSliderSplit", "Modify 3", \unipolar, "change3", "change3Min", "change3Max"],
				["TXMinMaxSliderSplit", "Modify 4", \unipolar, "change4", "change4Min", "change4Max"],
			];
		});

		if (displayOption == "5-8", {
			guiSpecArray = guiSpecArray ++[
				["TXMinMaxSliderSplit", "Modify 5", \unipolar, "change5", "change5Min", "change5Max"],
				["TXMinMaxSliderSplit", "Modify 6", \unipolar, "change6", "change6Min", "change6Max"],
				["TXMinMaxSliderSplit", "Modify 7", \unipolar, "change7", "change7Min", "change7Max"],
				["TXMinMaxSliderSplit", "Modify 8", \unipolar, "change8", "change8Min", "change8Max"],
			];
		});

		if (displayOption == "9-12", {
			guiSpecArray = guiSpecArray ++[
				["TXMinMaxSliderSplit", "Modify 9", \unipolar, "change9", "change9Min", "change9Max"],
				["TXMinMaxSliderSplit", "Modify 10", \unipolar, "change10", "change10Min", "change10Max"],
				["TXMinMaxSliderSplit", "Modify 11", \unipolar, "change11", "change11Min", "change11Max"],
				["TXMinMaxSliderSplit", "Modify 12", \unipolar, "change12", "change12Min", "change12Max"],
			];
		});

		if (displayOption == "13-16", {
			guiSpecArray = guiSpecArray ++[
				["TXMinMaxSliderSplit", "Modify 13", \unipolar, "change13", "change13Min", "change13Max"],
				["TXMinMaxSliderSplit", "Modify 14", \unipolar, "change14", "change14Min", "change14Max"],
				["TXMinMaxSliderSplit", "Modify 15", \unipolar, "change15", "change15Min", "change15Max"],
				["TXMinMaxSliderSplit", "Modify 16", \unipolar, "change16", "change16Min", "change16Max"],
			];
		});
	}

	getButtonColour { arg colour2Boolean;
		if (colour2Boolean == true, {
			^TXColor.sysGuiCol4;
		},{
			^TXColor.sysGuiCol1;
		});
	}

	evaluate {arg argText, showErrors = true;
		var compileResult;
		userFunctionString = argText;
		compileResult = argText.compile;
		if (compileResult.isNil, {
			userFuncCompileStatus = "ERROR: Code cannot compile - see post window.";
			if (showErrors, {
				userFuncCompileStatusView.string = userFuncCompileStatus;
			});
			validFunctionString = emptyFunctionString;
		},{
			userFuncCompileStatus = "Compiled OK.";
			if (showErrors, {
				userFuncCompileStatusView.string = userFuncCompileStatus;
			});
			validFunctionString = userFunctionString;
		});
		this.rebuildSynth;
	}

	extraSaveData { // override default method
		^[userFunctionString];
	}

	loadExtraData {arg argData;  // override default method
		this.evaluate(argData.at(0), false);
	}

}

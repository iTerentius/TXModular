// Example custom module wrapping a hand-written SynthDef.
// Not part of the stock TX Modular module set - a template to copy.

TXMySineSource : TXModuleBase {

	classvar <>classData;

	*initClass {
		classData = ();
		classData.arrInstances = [];
		classData.defaultName = "My Sine Source";
		classData.moduleRate = "audio";
		classData.moduleType = "source";
		classData.arrCtlSCInBusSpecs = [
			["Freq Mod", 1, "modFreq", 0],
			["Amp Mod", 1, "modAmp", 0],
		];
		classData.noOutChannels = 2;
		classData.arrOutBusSpecs = [
			["Out L + R", [0,1]],
			["Out L only", [0]],
			["Out R only", [1]]
		];
	}

	*new { arg argInstName;
		^super.new.init(argInstName);
	}

	init { arg argInstName;
		arrSynthArgSpecs = [
			["out", 0, 0],
			["freq", 0.3, defLagTime],
			["freqMin", 40, defLagTime],
			["freqMax", 2000, defLagTime],
			["amp", 0.3, defLagTime],
			["ampMin", 0, defLagTime],
			["ampMax", 1, defLagTime],
			["modFreq", 0, defLagTime],
			["modAmp", 0, defLagTime],
		];
		synthDefFunc = { arg out, freq, freqMin, freqMax, amp, ampMin, ampMax,
			modFreq = 0, modAmp = 0;
			var freqSum, ampSum, sig;
			var startEnv = TXEnvPresets.startEnvFunc.value;

			freqSum = freqMin + ((freqMax - freqMin) * (freq + modFreq).max(0).min(1));
			ampSum = ampMin + ((ampMax - ampMin) * (amp + modAmp).max(0).min(1));
			sig = SinOsc.ar(freqSum, 0, ampSum) ! 2;
			Out.ar(out, TXClean.ar(startEnv * sig));
		};
		guiSpecArray = [
			["TXMinMaxSliderSplit", "Freq", ControlSpec(20, 5000, \exp), "freq", "freqMin", "freqMax"],
			["TXMinMaxSliderSplit", "Amp", \unipolar, "amp", "ampMin", "ampMax"],
		];
		arrActionSpecs = this.buildActionSpecs(guiSpecArray);
		this.baseInit(this, argInstName);
		this.loadAndMakeSynth;
	}
}

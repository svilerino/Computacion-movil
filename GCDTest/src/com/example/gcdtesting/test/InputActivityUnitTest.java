package com.example.gcdtesting.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

import com.gcmovil.gcdtesting.input.ActivityInput;
import com.gcmovil.gcdtesting.output.OutputModel;

public class InputActivityUnitTest extends ActivityUnitTestCase<ActivityInput> {
	// This class provides ISOLATED testing of a single activity. 
    // The activity under test will be created with minimal connection to the system infrastructure.
    // The activity will not be running in the normal system and will not participate in the normal interactions with other Activities.

	private ActivityInput activity;

	public InputActivityUnitTest() {
		super(ActivityInput.class);
	}

	//el metodo setUp() se ejecuta antes de cada caso de test para inicializar el contexto de testing
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//lanzar la activity
		Intent intent = new Intent(getInstrumentation().getTargetContext(), ActivityInput.class);
		startActivity(intent, null, null);
		//obtener el activity
		activity = getActivity();
	}

	@Override
    protected void tearDown() {
        activity.finish();
    }

	/**
	 * •  El resultado es correcto
	 * 	Testeo esto directo contra el modelo: de esta forma sin importar la implementacion visual que le de al algoritmo es valido.
	 * 
	 * 		Modificar cualquier valor de los assert para ver que el test funciona bien 
	 */
	public void testMcdCorrecto(){
		OutputModel model = new OutputModel();
		assertEquals(model.makeGcd(1, 101), 1);
		assertEquals(model.makeGcd(2, 102), 2);
		assertEquals(model.makeGcd(3, 103), 1);
		assertEquals(model.makeGcd(4, 104), 4);
		assertEquals(model.makeGcd(5, 105), 5);
		assertEquals(model.makeGcd(6, 106), 2);
		assertEquals(model.makeGcd(7, 107), 1);
		assertEquals(model.makeGcd(8, 108), 4);
		assertEquals(model.makeGcd(9, 109), 1);
		assertEquals(model.makeGcd(10, 110), 10);
		assertEquals(model.makeGcd(11, 111), 1);
		assertEquals(model.makeGcd(12, 112), 4);
		assertEquals(model.makeGcd(13, 113), 1);
		assertEquals(model.makeGcd(14, 114), 2);
		assertEquals(model.makeGcd(15, 115), 5);
		assertEquals(model.makeGcd(16, 116), 4);
		assertEquals(model.makeGcd(17, 117), 1);
		assertEquals(model.makeGcd(18, 118), 2);
		assertEquals(model.makeGcd(19, 119), 1);
		assertEquals(model.makeGcd(20, 120), 20);
		assertEquals(model.makeGcd(21, 121), 1);
		assertEquals(model.makeGcd(22, 122), 2);
		assertEquals(model.makeGcd(23, 123), 1);
		assertEquals(model.makeGcd(24, 124), 4);
		assertEquals(model.makeGcd(25, 125), 25);
		assertEquals(model.makeGcd(26, 126), 2);
		assertEquals(model.makeGcd(27, 127), 1);
		assertEquals(model.makeGcd(28, 128), 4);
		assertEquals(model.makeGcd(29, 129), 1);
		assertEquals(model.makeGcd(30, 130), 10);
		assertEquals(model.makeGcd(31, 131), 1);
		assertEquals(model.makeGcd(32, 132), 4);
		assertEquals(model.makeGcd(33, 133), 1);
		assertEquals(model.makeGcd(34, 134), 2);
		assertEquals(model.makeGcd(35, 135), 5);
		assertEquals(model.makeGcd(36, 136), 4);
		assertEquals(model.makeGcd(37, 137), 1);
		assertEquals(model.makeGcd(38, 138), 2);
		assertEquals(model.makeGcd(39, 139), 1);
		assertEquals(model.makeGcd(40, 140), 20);
		assertEquals(model.makeGcd(41, 141), 1);
		assertEquals(model.makeGcd(42, 142), 2);
		assertEquals(model.makeGcd(43, 143), 1);
		assertEquals(model.makeGcd(44, 144), 4);
		assertEquals(model.makeGcd(45, 145), 5);
		assertEquals(model.makeGcd(46, 146), 2);
		assertEquals(model.makeGcd(47, 147), 1);
		assertEquals(model.makeGcd(48, 148), 4);
		assertEquals(model.makeGcd(49, 149), 1);
		assertEquals(model.makeGcd(50, 150), 50);
		assertEquals(model.makeGcd(51, 151), 1);
		assertEquals(model.makeGcd(52, 152), 4);
		assertEquals(model.makeGcd(53, 153), 1);
		assertEquals(model.makeGcd(54, 154), 2);
		assertEquals(model.makeGcd(55, 155), 5);
		assertEquals(model.makeGcd(56, 156), 4);
		assertEquals(model.makeGcd(57, 157), 1);
		assertEquals(model.makeGcd(58, 158), 2);
		assertEquals(model.makeGcd(59, 159), 1);
		assertEquals(model.makeGcd(60, 160), 20);
		assertEquals(model.makeGcd(61, 161), 1);
		assertEquals(model.makeGcd(62, 162), 2);
		assertEquals(model.makeGcd(63, 163), 1);
		assertEquals(model.makeGcd(64, 164), 4);
		assertEquals(model.makeGcd(65, 165), 5);
		assertEquals(model.makeGcd(66, 166), 2);
		assertEquals(model.makeGcd(67, 167), 1);
		assertEquals(model.makeGcd(68, 168), 4);
		assertEquals(model.makeGcd(69, 169), 1);
		assertEquals(model.makeGcd(70, 170), 10);
		assertEquals(model.makeGcd(71, 171), 1);
		assertEquals(model.makeGcd(72, 172), 4);
		assertEquals(model.makeGcd(73, 173), 1);
		assertEquals(model.makeGcd(74, 174), 2);
		assertEquals(model.makeGcd(75, 175), 25);
		assertEquals(model.makeGcd(76, 176), 4);
		assertEquals(model.makeGcd(77, 177), 1);
		assertEquals(model.makeGcd(78, 178), 2);
		assertEquals(model.makeGcd(79, 179), 1);
		assertEquals(model.makeGcd(80, 180), 20);
		assertEquals(model.makeGcd(81, 181), 1);
		assertEquals(model.makeGcd(82, 182), 2);
		assertEquals(model.makeGcd(83, 183), 1);
		assertEquals(model.makeGcd(84, 184), 4);
		assertEquals(model.makeGcd(85, 185), 5);
		assertEquals(model.makeGcd(86, 186), 2);
		assertEquals(model.makeGcd(87, 187), 1);
		assertEquals(model.makeGcd(88, 188), 4);
		assertEquals(model.makeGcd(89, 189), 1);
		assertEquals(model.makeGcd(90, 190), 10);
		assertEquals(model.makeGcd(91, 191), 1);
		assertEquals(model.makeGcd(92, 192), 4);
		assertEquals(model.makeGcd(93, 193), 1);
		assertEquals(model.makeGcd(94, 194), 2);
		assertEquals(model.makeGcd(95, 195), 5);
		assertEquals(model.makeGcd(96, 196), 4);
		assertEquals(model.makeGcd(97, 197), 1);
		assertEquals(model.makeGcd(98, 198), 2);
		assertEquals(model.makeGcd(99, 199), 1);
		assertEquals(model.makeGcd(100, 200), 100);
		assertEquals(model.makeGcd(101, 201), 1);
		assertEquals(model.makeGcd(102, 202), 2);
		assertEquals(model.makeGcd(103, 203), 1);
		assertEquals(model.makeGcd(104, 204), 4);
		assertEquals(model.makeGcd(105, 205), 5);
		assertEquals(model.makeGcd(106, 206), 2);
		assertEquals(model.makeGcd(107, 207), 1);
		assertEquals(model.makeGcd(108, 208), 4);
		assertEquals(model.makeGcd(109, 209), 1);
		assertEquals(model.makeGcd(110, 210), 10);
		assertEquals(model.makeGcd(111, 211), 1);
		assertEquals(model.makeGcd(112, 212), 4);
		assertEquals(model.makeGcd(113, 213), 1);
		assertEquals(model.makeGcd(114, 214), 2);
		assertEquals(model.makeGcd(115, 215), 5);
		assertEquals(model.makeGcd(116, 216), 4);
		assertEquals(model.makeGcd(117, 217), 1);
		assertEquals(model.makeGcd(118, 218), 2);
		assertEquals(model.makeGcd(119, 219), 1);
		assertEquals(model.makeGcd(120, 220), 20);
		assertEquals(model.makeGcd(121, 221), 1);
		assertEquals(model.makeGcd(122, 222), 2);
		assertEquals(model.makeGcd(123, 223), 1);
		assertEquals(model.makeGcd(124, 224), 4);
		assertEquals(model.makeGcd(125, 225), 25);
		assertEquals(model.makeGcd(126, 226), 2);
		assertEquals(model.makeGcd(127, 227), 1);
		assertEquals(model.makeGcd(128, 228), 4);
		assertEquals(model.makeGcd(129, 229), 1);
		assertEquals(model.makeGcd(130, 230), 10);
		assertEquals(model.makeGcd(131, 231), 1);
		assertEquals(model.makeGcd(132, 232), 4);
		assertEquals(model.makeGcd(133, 233), 1);
		assertEquals(model.makeGcd(134, 234), 2);
		assertEquals(model.makeGcd(135, 235), 5);
		assertEquals(model.makeGcd(136, 236), 4);
		assertEquals(model.makeGcd(137, 237), 1);
		assertEquals(model.makeGcd(138, 238), 2);
		assertEquals(model.makeGcd(139, 239), 1);
		assertEquals(model.makeGcd(140, 240), 20);
		assertEquals(model.makeGcd(141, 241), 1);
		assertEquals(model.makeGcd(142, 242), 2);
		assertEquals(model.makeGcd(143, 243), 1);
		assertEquals(model.makeGcd(144, 244), 4);
		assertEquals(model.makeGcd(145, 245), 5);
		assertEquals(model.makeGcd(146, 246), 2);
		assertEquals(model.makeGcd(147, 247), 1);
		assertEquals(model.makeGcd(148, 248), 4);
		assertEquals(model.makeGcd(149, 249), 1);
		assertEquals(model.makeGcd(150, 250), 50);
		assertEquals(model.makeGcd(151, 251), 1);
		assertEquals(model.makeGcd(152, 252), 4);
		assertEquals(model.makeGcd(153, 253), 1);
		assertEquals(model.makeGcd(154, 254), 2);
		assertEquals(model.makeGcd(155, 255), 5);
		assertEquals(model.makeGcd(156, 256), 4);
		assertEquals(model.makeGcd(157, 257), 1);
		assertEquals(model.makeGcd(158, 258), 2);
		assertEquals(model.makeGcd(159, 259), 1);
		assertEquals(model.makeGcd(160, 260), 20);
		assertEquals(model.makeGcd(161, 261), 1);
		assertEquals(model.makeGcd(162, 262), 2);
		assertEquals(model.makeGcd(163, 263), 1);
		assertEquals(model.makeGcd(164, 264), 4);
		assertEquals(model.makeGcd(165, 265), 5);
		assertEquals(model.makeGcd(166, 266), 2);
		assertEquals(model.makeGcd(167, 267), 1);
		assertEquals(model.makeGcd(168, 268), 4);
		assertEquals(model.makeGcd(169, 269), 1);
		assertEquals(model.makeGcd(170, 270), 10);
		assertEquals(model.makeGcd(171, 271), 1);
		assertEquals(model.makeGcd(172, 272), 4);
		assertEquals(model.makeGcd(173, 273), 1);
		assertEquals(model.makeGcd(174, 274), 2);
		assertEquals(model.makeGcd(175, 275), 25);
		assertEquals(model.makeGcd(176, 276), 4);
		assertEquals(model.makeGcd(177, 277), 1);
		assertEquals(model.makeGcd(178, 278), 2);
		assertEquals(model.makeGcd(179, 279), 1);
		assertEquals(model.makeGcd(180, 280), 20);
		assertEquals(model.makeGcd(181, 281), 1);
		assertEquals(model.makeGcd(182, 282), 2);
		assertEquals(model.makeGcd(183, 283), 1);
		assertEquals(model.makeGcd(184, 284), 4);
		assertEquals(model.makeGcd(185, 285), 5);
		assertEquals(model.makeGcd(186, 286), 2);
		assertEquals(model.makeGcd(187, 287), 1);
		assertEquals(model.makeGcd(188, 288), 4);
		assertEquals(model.makeGcd(189, 289), 1);
		assertEquals(model.makeGcd(190, 290), 10);
		assertEquals(model.makeGcd(191, 291), 1);
		assertEquals(model.makeGcd(192, 292), 4);
		assertEquals(model.makeGcd(193, 293), 1);
		assertEquals(model.makeGcd(194, 294), 2);
		assertEquals(model.makeGcd(195, 295), 5);
		assertEquals(model.makeGcd(196, 296), 4);
		assertEquals(model.makeGcd(197, 297), 1);
		assertEquals(model.makeGcd(198, 298), 2);
		assertEquals(model.makeGcd(199, 299), 1);
		assertEquals(model.makeGcd(200, 300), 100);
		assertEquals(model.makeGcd(201, 301), 1);
		assertEquals(model.makeGcd(202, 302), 2);
		assertEquals(model.makeGcd(203, 303), 1);
		assertEquals(model.makeGcd(204, 304), 4);
		assertEquals(model.makeGcd(205, 305), 5);
		assertEquals(model.makeGcd(206, 306), 2);
		assertEquals(model.makeGcd(207, 307), 1);
		assertEquals(model.makeGcd(208, 308), 4);
		assertEquals(model.makeGcd(209, 309), 1);
		assertEquals(model.makeGcd(210, 310), 10);
		assertEquals(model.makeGcd(211, 311), 1);
		assertEquals(model.makeGcd(212, 312), 4);
		assertEquals(model.makeGcd(213, 313), 1);
		assertEquals(model.makeGcd(214, 314), 2);
		assertEquals(model.makeGcd(215, 315), 5);
		assertEquals(model.makeGcd(216, 316), 4);
		assertEquals(model.makeGcd(217, 317), 1);
		assertEquals(model.makeGcd(218, 318), 2);
		assertEquals(model.makeGcd(219, 319), 1);
		assertEquals(model.makeGcd(220, 320), 20);
		assertEquals(model.makeGcd(221, 321), 1);
		assertEquals(model.makeGcd(222, 322), 2);
		assertEquals(model.makeGcd(223, 323), 1);
		assertEquals(model.makeGcd(224, 324), 4);
		assertEquals(model.makeGcd(225, 325), 25);
		assertEquals(model.makeGcd(226, 326), 2);
		assertEquals(model.makeGcd(227, 327), 1);
		assertEquals(model.makeGcd(228, 328), 4);
		assertEquals(model.makeGcd(229, 329), 1);
		assertEquals(model.makeGcd(230, 330), 10);
		assertEquals(model.makeGcd(231, 331), 1);
		assertEquals(model.makeGcd(232, 332), 4);
		assertEquals(model.makeGcd(233, 333), 1);
		assertEquals(model.makeGcd(234, 334), 2);
		assertEquals(model.makeGcd(235, 335), 5);
		assertEquals(model.makeGcd(236, 336), 4);
		assertEquals(model.makeGcd(237, 337), 1);
		assertEquals(model.makeGcd(238, 338), 2);
		assertEquals(model.makeGcd(239, 339), 1);
		assertEquals(model.makeGcd(240, 340), 20);
		assertEquals(model.makeGcd(241, 341), 1);
		assertEquals(model.makeGcd(242, 342), 2);
		assertEquals(model.makeGcd(243, 343), 1);
		assertEquals(model.makeGcd(244, 344), 4);
		assertEquals(model.makeGcd(245, 345), 5);
		assertEquals(model.makeGcd(246, 346), 2);
		assertEquals(model.makeGcd(247, 347), 1);
		assertEquals(model.makeGcd(248, 348), 4);
		assertEquals(model.makeGcd(249, 349), 1);
		assertEquals(model.makeGcd(250, 350), 50);
		assertEquals(model.makeGcd(251, 351), 1);
		assertEquals(model.makeGcd(252, 352), 4);
		assertEquals(model.makeGcd(253, 353), 1);
		assertEquals(model.makeGcd(254, 354), 2);
		assertEquals(model.makeGcd(255, 355), 5);
		assertEquals(model.makeGcd(256, 356), 4);
		assertEquals(model.makeGcd(257, 357), 1);
		assertEquals(model.makeGcd(258, 358), 2);
		assertEquals(model.makeGcd(259, 359), 1);
		assertEquals(model.makeGcd(260, 360), 20);
		assertEquals(model.makeGcd(261, 361), 1);
		assertEquals(model.makeGcd(262, 362), 2);
		assertEquals(model.makeGcd(263, 363), 1);
		assertEquals(model.makeGcd(264, 364), 4);
		assertEquals(model.makeGcd(265, 365), 5);
		assertEquals(model.makeGcd(266, 366), 2);
		assertEquals(model.makeGcd(267, 367), 1);
		assertEquals(model.makeGcd(268, 368), 4);
		assertEquals(model.makeGcd(269, 369), 1);
		assertEquals(model.makeGcd(270, 370), 10);
		assertEquals(model.makeGcd(271, 371), 1);
		assertEquals(model.makeGcd(272, 372), 4);
		assertEquals(model.makeGcd(273, 373), 1);
		assertEquals(model.makeGcd(274, 374), 2);
		assertEquals(model.makeGcd(275, 375), 25);
		assertEquals(model.makeGcd(276, 376), 4);
		assertEquals(model.makeGcd(277, 377), 1);
		assertEquals(model.makeGcd(278, 378), 2);
		assertEquals(model.makeGcd(279, 379), 1);
		assertEquals(model.makeGcd(280, 380), 20);
		assertEquals(model.makeGcd(281, 381), 1);
		assertEquals(model.makeGcd(282, 382), 2);
		assertEquals(model.makeGcd(283, 383), 1);
		assertEquals(model.makeGcd(284, 384), 4);
		assertEquals(model.makeGcd(285, 385), 5);
		assertEquals(model.makeGcd(286, 386), 2);
		assertEquals(model.makeGcd(287, 387), 1);
		assertEquals(model.makeGcd(288, 388), 4);
		assertEquals(model.makeGcd(289, 389), 1);
		assertEquals(model.makeGcd(290, 390), 10);
		assertEquals(model.makeGcd(291, 391), 1);
		assertEquals(model.makeGcd(292, 392), 4);
		assertEquals(model.makeGcd(293, 393), 1);
		assertEquals(model.makeGcd(294, 394), 2);
		assertEquals(model.makeGcd(295, 395), 5);
		assertEquals(model.makeGcd(296, 396), 4);
		assertEquals(model.makeGcd(297, 397), 1);
		assertEquals(model.makeGcd(298, 398), 2);
		assertEquals(model.makeGcd(299, 399), 1);
		assertEquals(model.makeGcd(300, 400), 100);
		assertEquals(model.makeGcd(301, 401), 1);
		assertEquals(model.makeGcd(302, 402), 2);
		assertEquals(model.makeGcd(303, 403), 1);
		assertEquals(model.makeGcd(304, 404), 4);
		assertEquals(model.makeGcd(305, 405), 5);
		assertEquals(model.makeGcd(306, 406), 2);
		assertEquals(model.makeGcd(307, 407), 1);
		assertEquals(model.makeGcd(308, 408), 4);
		assertEquals(model.makeGcd(309, 409), 1);
		assertEquals(model.makeGcd(310, 410), 10);
		assertEquals(model.makeGcd(311, 411), 1);
		assertEquals(model.makeGcd(312, 412), 4);
		assertEquals(model.makeGcd(313, 413), 1);
		assertEquals(model.makeGcd(314, 414), 2);
		assertEquals(model.makeGcd(315, 415), 5);
		assertEquals(model.makeGcd(316, 416), 4);
		assertEquals(model.makeGcd(317, 417), 1);
		assertEquals(model.makeGcd(318, 418), 2);
		assertEquals(model.makeGcd(319, 419), 1);
		assertEquals(model.makeGcd(320, 420), 20);
		assertEquals(model.makeGcd(321, 421), 1);
		assertEquals(model.makeGcd(322, 422), 2);
		assertEquals(model.makeGcd(323, 423), 1);
		assertEquals(model.makeGcd(324, 424), 4);
		assertEquals(model.makeGcd(325, 425), 25);
		assertEquals(model.makeGcd(326, 426), 2);
		assertEquals(model.makeGcd(327, 427), 1);
		assertEquals(model.makeGcd(328, 428), 4);
		assertEquals(model.makeGcd(329, 429), 1);
		assertEquals(model.makeGcd(330, 430), 10);
		assertEquals(model.makeGcd(331, 431), 1);
		assertEquals(model.makeGcd(332, 432), 4);
		assertEquals(model.makeGcd(333, 433), 1);
		assertEquals(model.makeGcd(334, 434), 2);
		assertEquals(model.makeGcd(335, 435), 5);
		assertEquals(model.makeGcd(336, 436), 4);
		assertEquals(model.makeGcd(337, 437), 1);
		assertEquals(model.makeGcd(338, 438), 2);
		assertEquals(model.makeGcd(339, 439), 1);
		assertEquals(model.makeGcd(340, 440), 20);
		assertEquals(model.makeGcd(341, 441), 1);
		assertEquals(model.makeGcd(342, 442), 2);
		assertEquals(model.makeGcd(343, 443), 1);
		assertEquals(model.makeGcd(344, 444), 4);
		assertEquals(model.makeGcd(345, 445), 5);
		assertEquals(model.makeGcd(346, 446), 2);
		assertEquals(model.makeGcd(347, 447), 1);
		assertEquals(model.makeGcd(348, 448), 4);
		assertEquals(model.makeGcd(349, 449), 1);
		assertEquals(model.makeGcd(350, 450), 50);
		assertEquals(model.makeGcd(351, 451), 1);
		assertEquals(model.makeGcd(352, 452), 4);
		assertEquals(model.makeGcd(353, 453), 1);
		assertEquals(model.makeGcd(354, 454), 2);
		assertEquals(model.makeGcd(355, 455), 5);
		assertEquals(model.makeGcd(356, 456), 4);
		assertEquals(model.makeGcd(357, 457), 1);
		assertEquals(model.makeGcd(358, 458), 2);
		assertEquals(model.makeGcd(359, 459), 1);
		assertEquals(model.makeGcd(360, 460), 20);
		assertEquals(model.makeGcd(361, 461), 1);
		assertEquals(model.makeGcd(362, 462), 2);
		assertEquals(model.makeGcd(363, 463), 1);
		assertEquals(model.makeGcd(364, 464), 4);
		assertEquals(model.makeGcd(365, 465), 5);
		assertEquals(model.makeGcd(366, 466), 2);
		assertEquals(model.makeGcd(367, 467), 1);
		assertEquals(model.makeGcd(368, 468), 4);
		assertEquals(model.makeGcd(369, 469), 1);
		assertEquals(model.makeGcd(370, 470), 10);
		assertEquals(model.makeGcd(371, 471), 1);
		assertEquals(model.makeGcd(372, 472), 4);
		assertEquals(model.makeGcd(373, 473), 1);
		assertEquals(model.makeGcd(374, 474), 2);
		assertEquals(model.makeGcd(375, 475), 25);
		assertEquals(model.makeGcd(376, 476), 4);
		assertEquals(model.makeGcd(377, 477), 1);
		assertEquals(model.makeGcd(378, 478), 2);
		assertEquals(model.makeGcd(379, 479), 1);
		assertEquals(model.makeGcd(380, 480), 20);
		assertEquals(model.makeGcd(381, 481), 1);
		assertEquals(model.makeGcd(382, 482), 2);
		assertEquals(model.makeGcd(383, 483), 1);
		assertEquals(model.makeGcd(384, 484), 4);
		assertEquals(model.makeGcd(385, 485), 5);
		assertEquals(model.makeGcd(386, 486), 2);
		assertEquals(model.makeGcd(387, 487), 1);
		assertEquals(model.makeGcd(388, 488), 4);
		assertEquals(model.makeGcd(389, 489), 1);
		assertEquals(model.makeGcd(390, 490), 10);
		assertEquals(model.makeGcd(391, 491), 1);
		assertEquals(model.makeGcd(392, 492), 4);
		assertEquals(model.makeGcd(393, 493), 1);
		assertEquals(model.makeGcd(394, 494), 2);
		assertEquals(model.makeGcd(395, 495), 5);
		assertEquals(model.makeGcd(396, 496), 4);
		assertEquals(model.makeGcd(397, 497), 1);
		assertEquals(model.makeGcd(398, 498), 2);
		assertEquals(model.makeGcd(399, 499), 1);
		assertEquals(model.makeGcd(400, 500), 100);
		assertEquals(model.makeGcd(401, 501), 1);
		assertEquals(model.makeGcd(402, 502), 2);
		assertEquals(model.makeGcd(403, 503), 1);
		assertEquals(model.makeGcd(404, 504), 4);
		assertEquals(model.makeGcd(405, 505), 5);
		assertEquals(model.makeGcd(406, 506), 2);
		assertEquals(model.makeGcd(407, 507), 1);
		assertEquals(model.makeGcd(408, 508), 4);
		assertEquals(model.makeGcd(409, 509), 1);
		assertEquals(model.makeGcd(410, 510), 10);
		assertEquals(model.makeGcd(411, 511), 1);
		assertEquals(model.makeGcd(412, 512), 4);
		assertEquals(model.makeGcd(413, 513), 1);
		assertEquals(model.makeGcd(414, 514), 2);
		assertEquals(model.makeGcd(415, 515), 5);
		assertEquals(model.makeGcd(416, 516), 4);
		assertEquals(model.makeGcd(417, 517), 1);
		assertEquals(model.makeGcd(418, 518), 2);
		assertEquals(model.makeGcd(419, 519), 1);
		assertEquals(model.makeGcd(420, 520), 20);
		assertEquals(model.makeGcd(421, 521), 1);
		assertEquals(model.makeGcd(422, 522), 2);
		assertEquals(model.makeGcd(423, 523), 1);
		assertEquals(model.makeGcd(424, 524), 4);
		assertEquals(model.makeGcd(425, 525), 25);
		assertEquals(model.makeGcd(426, 526), 2);
		assertEquals(model.makeGcd(427, 527), 1);
		assertEquals(model.makeGcd(428, 528), 4);
		assertEquals(model.makeGcd(429, 529), 1);
		assertEquals(model.makeGcd(430, 530), 10);
		assertEquals(model.makeGcd(431, 531), 1);
		assertEquals(model.makeGcd(432, 532), 4);
		assertEquals(model.makeGcd(433, 533), 1);
		assertEquals(model.makeGcd(434, 534), 2);
		assertEquals(model.makeGcd(435, 535), 5);
		assertEquals(model.makeGcd(436, 536), 4);
		assertEquals(model.makeGcd(437, 537), 1);
		assertEquals(model.makeGcd(438, 538), 2);
		assertEquals(model.makeGcd(439, 539), 1);
		assertEquals(model.makeGcd(440, 540), 20);
		assertEquals(model.makeGcd(441, 541), 1);
		assertEquals(model.makeGcd(442, 542), 2);
		assertEquals(model.makeGcd(443, 543), 1);
		assertEquals(model.makeGcd(444, 544), 4);
		assertEquals(model.makeGcd(445, 545), 5);
		assertEquals(model.makeGcd(446, 546), 2);
		assertEquals(model.makeGcd(447, 547), 1);
		assertEquals(model.makeGcd(448, 548), 4);
		assertEquals(model.makeGcd(449, 549), 1);
		assertEquals(model.makeGcd(450, 550), 50);
		assertEquals(model.makeGcd(451, 551), 1);
		assertEquals(model.makeGcd(452, 552), 4);
		assertEquals(model.makeGcd(453, 553), 1);
		assertEquals(model.makeGcd(454, 554), 2);
		assertEquals(model.makeGcd(455, 555), 5);
		assertEquals(model.makeGcd(456, 556), 4);
		assertEquals(model.makeGcd(457, 557), 1);
		assertEquals(model.makeGcd(458, 558), 2);
		assertEquals(model.makeGcd(459, 559), 1);
		assertEquals(model.makeGcd(460, 560), 20);
		assertEquals(model.makeGcd(461, 561), 1);
		assertEquals(model.makeGcd(462, 562), 2);
		assertEquals(model.makeGcd(463, 563), 1);
		assertEquals(model.makeGcd(464, 564), 4);
		assertEquals(model.makeGcd(465, 565), 5);
		assertEquals(model.makeGcd(466, 566), 2);
		assertEquals(model.makeGcd(467, 567), 1);
		assertEquals(model.makeGcd(468, 568), 4);
		assertEquals(model.makeGcd(469, 569), 1);
		assertEquals(model.makeGcd(470, 570), 10);
		assertEquals(model.makeGcd(471, 571), 1);
		assertEquals(model.makeGcd(472, 572), 4);
		assertEquals(model.makeGcd(473, 573), 1);
		assertEquals(model.makeGcd(474, 574), 2);
		assertEquals(model.makeGcd(475, 575), 25);
		assertEquals(model.makeGcd(476, 576), 4);
		assertEquals(model.makeGcd(477, 577), 1);
		assertEquals(model.makeGcd(478, 578), 2);
		assertEquals(model.makeGcd(479, 579), 1);
		assertEquals(model.makeGcd(480, 580), 20);
		assertEquals(model.makeGcd(481, 581), 1);
		assertEquals(model.makeGcd(482, 582), 2);
		assertEquals(model.makeGcd(483, 583), 1);
		assertEquals(model.makeGcd(484, 584), 4);
		assertEquals(model.makeGcd(485, 585), 5);
		assertEquals(model.makeGcd(486, 586), 2);
		assertEquals(model.makeGcd(487, 587), 1);
		assertEquals(model.makeGcd(488, 588), 4);
		assertEquals(model.makeGcd(489, 589), 1);
		assertEquals(model.makeGcd(490, 590), 10);
		assertEquals(model.makeGcd(491, 591), 1);
		assertEquals(model.makeGcd(492, 592), 4);
		assertEquals(model.makeGcd(493, 593), 1);
		assertEquals(model.makeGcd(494, 594), 2);
		assertEquals(model.makeGcd(495, 595), 5);
		assertEquals(model.makeGcd(496, 596), 4);
		assertEquals(model.makeGcd(497, 597), 1);
		assertEquals(model.makeGcd(498, 598), 2);
		assertEquals(model.makeGcd(499, 599), 1);
		assertEquals(model.makeGcd(500, 600), 100);
	}
}
